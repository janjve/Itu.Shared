package itu.datamining.week2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by jan on 2/10/2016.
 */
public class Preprocessor {

    // Assumes first row are the header.
    public static String[][] selectFrom(String[][] data, String... selectedAttributes){
        // Generate attribute index.
        Map<String, Integer> attributeIndex = new HashMap();
        for(int i = 0; i<data[0].length; i++){
            for(String selectedAttribute: selectedAttributes){
                boolean found = selectedAttribute.equals(data[0][i]);
                if(found){
                    attributeIndex.put(selectedAttribute, i);
                    break;
                }
            }
        }

        // Setup data structure.
        String[][] result = new String[data.length][selectedAttributes.length];

        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < selectedAttributes.length; j++){
                int index = attributeIndex.get(selectedAttributes[j]);
                result[i][j] = data[i][index];
            }
        }

        return result;
    }

    public static String[][] trimTrailingValues(String[][] data, String attribute){
        int index = getAttributeIndex(data[0], attribute)[0];

        for (String[] tuple: data) {
            String[] tempTuple = tuple[index].split(",",2);
            tuple[index] = tempTuple[0];
            if(tempTuple.length > 1) {
                tuple[index] += "\"";
            }
        }
        return data;
    }

    public static String[][] convertLettersToNumbers(String[][] data, String attribute) {
        int index = getAttributeIndex(data[0], attribute)[0];

        for (String[] tuple: data) {
            tuple[index].toLowerCase();
            boolean isInteger = isInteger(tuple[index], 10);
            if (!isInteger) {
                if(tuple[index].length() > 1) {
                    tuple[index] = "-1";
                } else {
                    char[] c = tuple[index].toCharArray();
                    int temp = (int)c[1];
                    tuple[index] = "" + (temp - 96);
                }
            }
        }
        return null;
    }


    //Helper method
    private static int[] getAttributeIndex(String[] headerData, String... attributes){
        int[] indexes = new int[attributes.length];

        for(int i = 0; i<headerData.length; i++){
            for(int j = 0; j < attributes.length; j++){
                if(attributes[j].equals(headerData[i])){
                    indexes[j] = i;
                    break;
                }
            }
        }
        return indexes;
    }

    //Helper method
    public static boolean isInteger(String s, int radix) {
        Scanner sc = new Scanner(s.trim());
        if(!sc.hasNextInt(radix)) return false;
        // we know it starts with a valid int, now make sure
        // there's nothing left!
        sc.nextInt(radix);
        return !sc.hasNext();
    }
}
