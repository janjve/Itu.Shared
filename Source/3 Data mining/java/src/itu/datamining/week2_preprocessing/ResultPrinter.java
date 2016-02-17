package itu.datamining.week2_preprocessing;

import java.util.Arrays;

/**
 * Created by jan on 2/10/2016.
 */
public class ResultPrinter {
    private static final String RESULT_HEADER_DIVIDER = "==========================================================================================";

    public static void printResult(String[][] data, boolean skipHeaderRow){
        for (String[] line : data) {
            System.out.println(Arrays.toString(line));
        }
        System.out.println("Number of tuples loaded: "+data.length);
        if (!skipHeaderRow)
            System.out.println("(First row is data lables)");
    }

    public static void printHeader(String header) {
        System.out.println(RESULT_HEADER_DIVIDER);
        System.out.println(header);
        System.out.println(RESULT_HEADER_DIVIDER);
    }
}
