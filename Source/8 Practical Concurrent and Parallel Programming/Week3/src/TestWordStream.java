// Week 3
// sestoft@itu.dk * 2015-09-09

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestWordStream {
    public static void main(String[] args) {
        String filename = "C:\\Code\\Itu.Shared\\Source\\8 Practical Concurrent and Parallel Programming\\Week3\\src\\words";
        //Exercise 3.1
        Stream<String> stream = readWords(filename);
        //System.out.println(stream.count());

        //Exercise 3.2
        //stream.limit(100).forEach(System.out::println);

        //Exercise 3.3
        //Stream<String> stream1 = stream.filter(x -> x.length() == 22);
        //stream1.forEach(System.out::println);

        //Exercise 3.4
        //stream1.findAny();

        //Exercise 3.5
        //Stream<String> stream2 = stream.filter(x -> isPalindrome(x));
        //stream2.forEach(System.out::println);

        //Exercise 3.6
        //Stream<String> stream3 = stream2.parallel();
        //stream2.forEach(System.out::println);

        //Exercise 3.7
        //WordSummary ws = stream.collect(WordSummary::new, WordSummary::accept, WordSummary::combine);
        //System.out.println(ws.max + "     " + ws.min + "    " + ws.sum/ws.count);

        //Exercise 3.8
        //Map<Integer, List<String>> stream4 = stream.collect(Collectors.groupingBy(x -> x.length()));
        //stream4.forEach((k,v) -> v.forEach(System.out::println));

        //Exercise 3.9
        Stream<Map<Character, Integer>> stream5 = stream.map(TestWordStream::letters);//.limit(100);
        //stream5.forEach((x) -> x.forEach((k,v) -> System.out.println(k + "  " + v)));

        //Exercise 3.10
        Integer res = stream5.map(x -> x.containsKey('e') ? x.get('e') : 0).reduce(0, (a, z) -> a + z);
        System.out.println(res);

    }


    public static Stream<String> readWords(String filename) {
        try {
            return Files.lines(Paths.get(filename));
        } catch (IOException exn) {
            return Stream.<String>empty();
        }
    }

    public static boolean isPalindrome(String s) {
        for(int i = 0; i < s.length()/2; i++) {
            if(s.charAt(i) != s.charAt(s.length()-i-1))
                return false;
        }
        return true;
    }

    public static Map<Character, Integer> letters(String s) {
        Map<Character, Integer> res = new TreeMap<>();
        for(int i = 0; i < s.length(); i++) {
            char key = Character.toLowerCase(s.charAt(i));
            res.put(key, res.containsKey(key) ? res.get(key) + 1 : 1);
        }
        return res;
    }
}

class WordSummary implements Consumer<String>{
    public int min, max, sum, count;
    public WordSummary() {
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        sum = 0;
        count = 0;
    }

    @Override
    public void accept(String s) {
        int length = s.length();
        min = Math.min(min,length);
        max = Math.max(max,length);
        sum += length;
        count ++;
    }

    public void combine(WordSummary a) {
        min = Math.min(min,a.min);
        max = Math.max(max,a.max);
        sum += a.sum;
        count += a.count;
    }
}
