package itu.datamining.week2;


import itu.datamining.utility.CSVFileReader;

import java.io.IOException;

/**
 * Created by jan on 2/10/2016.
 */
public class MainWeek2 {
    private static final String FILE_PATH = "DataMining 2016 (Responses).csv";
    private static final String CSV_DIVIDER = ";";
    private static final String NULL_VALUE = "-";
    private static final boolean SKIP_HEADER_ROW = false;

    public static void main(String[] args){
        boolean skipHeaderRow = false;

        try {
            String[][] data = CSVFileReader.readDataFile(FILE_PATH,
                    CSV_DIVIDER,
                    NULL_VALUE,
                    SKIP_HEADER_ROW);

            // Data as is.
            ResultPrinter.printHeader("Loaded data");
            ResultPrinter.printResult(data, SKIP_HEADER_ROW);

            // Attribute subset selection
            String[][] result = Preprocessor.selectFrom(data,
                    "\"What degree are you studying?\"",
                    "\"How often do you play video games?\"",
                    "\"Favorite game?\"",
                    "\"Which row are you sitting/did you sit in during the introduction lecture? \"",
                    "\"Which seat are you sitting/did you sit on during the introduction lecture?\"");

            ResultPrinter.printHeader("Attribute reduced selection");
            ResultPrinter.printResult(result, !SKIP_HEADER_ROW);

            Preprocessor.trimTrailingValues(result, "\"Favorite game?\"");

            ResultPrinter.printHeader("Trimmed Favorite Game");
            ResultPrinter.printResult(result, !SKIP_HEADER_ROW);

            Preprocessor.convertLettersToNumbers(result, "\"Which row are you sitting/did you sit in during the introduction lecture? \"");

            ResultPrinter.printHeader("Row letters to numbers");
            ResultPrinter.printResult(result, !SKIP_HEADER_ROW);



        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }


}
