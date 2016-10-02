// For week 5
// sestoft@itu.dk * 2014-09-19

import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class TestDownload {

  private static final String[] urls = 
  { "http://www.itu.dk", "http://www.di.ku.dk", "http://www.miele.de",
    "http://www.business.dk", "http://www.xkcd.com", "http://www.dr.dk",
    "http://www.vg.no", "http://www.tv2.dk", "http://www.google.com",
    "http://www.dr.dk", "http://www.dtu.dk", "http://www.eb.dk", 
    "http://www.nytimes.com", "http://www.ibm.com", "http://www.lemonde.fr",   
    "http://www.imdb.net", "http://www.dn.se", "http://www.heise.de", "http://www.wsj.com", 
    "http://www.bbc.co.uk", "http://www.dsb.dk", "http://www.bmw.com", "https://www.cia.gov" 
  };

  public static void main(String[] args) throws IOException {
    // 5.3.1
    /*String url = "https://www.wikipedia.org/";
    String page = getPage(url, 10);
    System.out.printf("%-30s%n%s%n", url, page);*/

    // 5.3.2 - 5.3.3
    /*
    Timer t = new Timer();
    Map<String, String> pages = getPages(urls, 200);
    double spend = t.check();
    printMap(pages, spend);
    */

    // 5.3.4
    
    Timer t = new Timer();
    Map<String, String> pages = getPagesParallel(urls, 200);
    double spend = t.check();
    printMap(pages, spend);

  }

  public static void printMap(Map<String, String> pages, double time){
    for (Map.Entry<String, String> entry : pages.entrySet())
      System.out.println(entry.getKey() + "\t" + entry.getValue().length());

    System.out.println("");
    System.out.println("Retrieved in: " + spend);
  }

  public static Map<String, String> getPages(String[] urls, int maxLines)throws IOException {
    Map<String, String> pages = new HashMap<String, String>();
    for(int i = 0; i < urls.length; i++){
      pages.put(urls[i], getPage(urls[i], maxLines));
    }
    return pages;
  }

  public static Map<String, String> getPagesParallel(String[] urls, int maxLines)throws IOException {
    Map<String, String> pages = new HashMap<String, String>();
    List<Callable<DownloadTuple>> tasks = new ArrayList<Callable<DownloadTuple>>();
    final int ml = maxLines;
    final ExecutorService executor = Executors.newWorkStealingPool();
    for(int i = 0; i < urls.length; i++){
      final String url = urls[i];
      tasks.add(() -> {
        return new DownloadTuple(url, getPage(url, ml));
      });
    }

    try{
    List<Future<DownloadTuple>> futures = executor.invokeAll(tasks);
    
    for(Future<DownloadTuple> fut : futures){
      DownloadTuple sjw = fut.get();
      pages.put(sjw.url, sjw.page);
    }
    return pages;
    } catch(Exception e){ throw new IOException();}
  }

  public static String getPage(String url, int maxLines) throws IOException {
    // This will close the streams after use (JLS 8 para 14.20.3):
    try (BufferedReader in 
         = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
      StringBuilder sb = new StringBuilder();
      for (int i=0; i<maxLines; i++) {
        String inputLine = in.readLine();
        if (inputLine == null)
          break;
        else
          sb.append(inputLine).append("\n");
      }
      return sb.toString();
    }
  }
}

class DownloadTuple {
  public String url;
  public String page;

  public DownloadTuple(String u, String p){
    url = u;
    page = p;
  }
}