package itu.datamining.week4;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
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

//        ItemSet canJoin = joinSets(new ItemSet(new int[]{1,2,3,4}), new ItemSet(new int[]{1,2,3,5}));
//        System.out.println("Set: " + canJoin);

        Hashtable<ItemSet, Integer> itemsetslevel1 = generateFrequentItemSetsLevel1(TRANSACTIONS, 7);
//        for (ItemSet key: itemsetslevel1.keySet()) {
//            System.out.println("Key: " + key.set[0] + " Value: " + itemsetslevel1.get(key));
//        }
//        System.out.println("hashtable size: " + itemsetslevel1.size());
        Hashtable<ItemSet, Integer> itemsetslevel2 = generateFrequentItemSets(7, TRANSACTIONS, itemsetslevel1);
        for (ItemSet key: itemsetslevel2.keySet()) {
            System.out.println("Key: " + key.set[0] + " Value: " + itemsetslevel2.get(key));
        }
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
        // Find candidate from join
        ArrayList<ItemSet> lowerLevelitemSet = new ArrayList<>(lowerLevelItemSets.keySet());
        ArrayList<ItemSet> currentLevelItemSet = new ArrayList<>();

        for (int i=0; i < lowerLevelitemSet.size(); i++){
            for (int j = 0; j < lowerLevelitemSet.size(); j++){
                ItemSet joinedItemSet = joinSets(lowerLevelitemSet.get(i), lowerLevelitemSet.get(j));
                if(joinedItemSet != null)
                    currentLevelItemSet.add(joinedItemSet);
            }
        }

        // Count in dataset D
        Hashtable<ItemSet, Integer> candidateSet = new Hashtable<>();

        for(int i = 0; i < transactions.length; i++){
            int itemCount = 0;
            for (int j = 0; j < transactions[i].length; j++){
                for (int k = 0; k < currentLevelItemSet.size(); k++){
                    for(int l = 0; l < currentLevelItemSet.get(k).set.length; l++)
                        if(transactions[i][j] == currentLevelItemSet.get(k).set[l]){
                            if(!candidateSet.containsKey(currentLevelItemSet.get(k))){
                                candidateSet.put(currentLevelItemSet.get(k), 0);
                            }
                            candidateSet.put(currentLevelItemSet.get(k), candidateSet.get(currentLevelItemSet.get(k))+1);
                        }
                }
            }
        }
        // Remove lower from threshold
        Hashtable<ItemSet, Integer> frequentItemSets = new Hashtable<>();

        for (ItemSet key: frequentItemSets.keySet()) {
            int value = frequentItemSets.get(key);
            if(value >= supportThreshold){
                frequentItemSets.put(key, value);
            }
        }
        return frequentItemSets;

        // TODO: first generate candidate itemsets from the lower level itemsets


        /*
         * TODO: now check the support for all candidates and add only those
         * that have enough support to the set
         */

        // TODO: return something useful
        //return null;
    }

    private static ItemSet joinSets( ItemSet first, ItemSet second ) {
        if(first.set.length != second.set.length) return null;

        int[] joinedSet = new int[first.set.length+1];

        for (int i = 0; i < first.set.length; i++){
            if(i == first.set.length-1){
                if(first.set[i] == second.set[i]) return null;

                joinedSet[i] = first.set[i];
                joinedSet[i+1] = second.set[i];
                break;
            } else if(first.set[i] == second.set[i]) {
                joinedSet[i] = first.set[i];
            } else return null;
        }
        return new ItemSet(joinedSet);
    }

    private static Hashtable<ItemSet, Integer> generateFrequentItemSetsLevel1( int[][] transactions, int supportThreshold ) {
        Hashtable<ItemSet, Integer> itemSets = new Hashtable<ItemSet, Integer>();
        for(int i = 0; i < transactions.length; i++){
            for (int j = 0; j < transactions[i].length; j++){
                ItemSet itemSet = new ItemSet(new int[]{transactions[i][j]});
                if(!itemSets.containsKey(itemSet)){
                    itemSets.put(itemSet, 0);
                }
                itemSets.put(itemSet, itemSets.get(itemSet)+1);
            }
        }

        Hashtable<ItemSet, Integer> frequentItemSets = new Hashtable<ItemSet, Integer>();

        for (ItemSet key: itemSets.keySet()) {
            int value = itemSets.get(key);
            if(value >= supportThreshold){
                frequentItemSets.put(key, value);
            }
        }
        return frequentItemSets;
    }

    private static int countSupport( int[] itemSet, int[][] transactions ) {
        int countSupport = 0;
        for (int i = 0; i < transactions.length;i++){
            int[] currentTransaction = transactions[i];
            if(currentTransaction.length < itemSet.length) continue;

            int matchedItemCount = 0;
            for (int j = 0; j < currentTransaction.length && matchedItemCount < itemSet.length; j++){
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
