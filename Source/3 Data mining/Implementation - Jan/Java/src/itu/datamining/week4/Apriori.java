package itu.datamining.week4;

import java.util.Hashtable;
import java.util.List;


public class Apriori {
	/***
	 * The TRANSACTIONS 2-dimensional array holds the full data set for the lab
	 */
    static final int[][] TRANSACTIONS = new int[][] { { 1, 2, 3, 4, 5 }, { 1, 3, 5 }, { 2, 3, 5 }, { 1, 5 }, { 1, 3, 4 }, { 2, 3, 5 }, { 2, 3, 5 },
                    { 3, 4, 5 }, { 4, 5 }, { 2 }, { 2, 3 }, { 2, 3, 4 }, { 3, 4, 5 } };
                    
    static final int[][] BOOK_TRANSACTIONS = new int[][] { { 1, 2, 5 }, {2, 4}, { 2, 3 }, { 1, 2, 4 }, { 1, 3 }, { 2, 3 }, { 1, 3 },
                    { 1, 2, 3, 5 }, { 1, 2, 3 }};

    public static void main( String[] args ) {
        // TODO: Select a reasonable support threshold via trial-and-error. Can either be percentage or absolute value.
        //final int supportThreshold = 42;
        //apriori( TRANSACTIONS, supportThreshold );

//        int supportCount = countSupport(new int[]{2,3}, TRANSACTIONS);
//        System.out.println("Support Count: " + supportCount);


    }

    public static List<ItemSet> apriori( int[][] transactions, int supportThreshold ) {
        int k;
        Hashtable<ItemSet, Integer> frequentItemSets = generateFrequentItemSetsLevel1( transactions, supportThreshold );
        for (k = 1; frequentItemSets.size() > 0; k++) {
            System.out.print( "Finding frequent itemsets of length " + (k + 1) + "…" );
            frequentItemSets = generateFrequentItemSets( supportThreshold, transactions, frequentItemSets );
            // TODO: add to list

            System.out.println( " found " + frequentItemSets.size() );
        }
        // TODO: create association rules from the frequent itemsets

        // TODO: return something useful
        return null;
    }

    private static Hashtable<ItemSet, Integer> generateFrequentItemSets( int supportThreshold, int[][] transactions,
                    Hashtable<ItemSet, Integer> lowerLevelItemSets ) {
        // TODO: first generate candidate itemsets from the lower level itemsets

        /*
         * TODO: now check the support for all candidates and add only those
         * that have enough support to the set
         */

        // TODO: return something useful
        return null;
    }

    private static ItemSet joinSets( ItemSet first, ItemSet second ) {
        // TODO: return something useful
        return null;
    }

    private static Hashtable<ItemSet, Integer> generateFrequentItemSetsLevel1( int[][] transactions, int supportThreshold ) {
        // TODO: return something useful
        return null;
    }

    private static int countSupport( int[] itemSet, int[][] transactions ) {
        int countSupport = 0;
        for (int i = 0; i < transactions.length;i++){
            int[] currentTransaction = transactions[i];
            if(currentTransaction.length < itemSet.length) continue;

            int matchedItemCount = 0;
            for (int j = 0; j < currentTransaction.length; j++){
                for (int k = 0; k < itemSet.length; k++){
                    if(currentTransaction[j] == itemSet[k]){
                        matchedItemCount++;
                    }
                }
            }
            if(matchedItemCount == itemSet.length){
                countSupport++;
            }
        }

        return countSupport;
    }

}
