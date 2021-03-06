package itu.datamining.week4_pattern_and_association_mining;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


public class Apriori {
	/***
	 * The TRANSACTIONS 2-dimensional array holds the full data set for the lab
	 */
    static final int[][] TRANSACTIONS = new int[][] {{ 1, 2, 3, 4, 5 }, { 1, 3, 5 }, { 2, 3, 5 }, { 1, 5 }, { 1, 3, 4 }, { 2, 3, 5 },
            { 2, 3, 5 }, { 3, 4, 5 }, { 4, 5 }, { 2 }, { 2, 3 }, { 2, 3, 4 }, { 3, 4, 5 } };
                    
    static final int[][] BOOK_TRANSACTIONS = new int[][] { { 1, 2, 5 }, {2, 4}, { 2, 3 }, { 1, 2, 4 }, { 1, 3 }, { 2, 3 }, { 1, 3 },
                    { 1, 2, 3, 5 }, { 1, 2, 3 }};

    public static void main( String[] args ) {
        // TODO: Select a reasonable support threshold via trial-and-error. Can either be percentage or absolute value.
        //final int supportThreshold = 42;
        //apriori( TRANSACTIONS, supportThreshold );
        Hashtable<ItemSet, Integer> table = generateFrequentItemSetsLevel1(TRANSACTIONS, 7);
        Hashtable<ItemSet, Integer> table1 = generateFrequentItemSets(7,TRANSACTIONS,table);
        System.out.println(table.size());

    }
    /*
    * We should check that the following properties are true for the Apriori alg:
    * Itemsets should be pruned, such that non-frequent itemsets are not part of the generated itemsets. TODO!!
    * It should finish when there is only 1 element left!
    * Should it return all itemsets that are frequent, no matter how small?
    * What should this algorithm ACTUALLY return?!
    * */

    public static List<ItemSet> apriori( int[][] transactions, int supportThreshold ) {
        int k = 1;
        Hashtable<ItemSet, Integer> frequentItemSets = generateFrequentItemSetsLevel1( transactions, supportThreshold );
        ArrayList<ItemSet> itemSets = new ArrayList<>();
        //Continues untill there is only one itemset left.

        while(frequentItemSets.size() > 1) {
            k++;
            System.out.print( "Finding frequent itemsets of length " + (k + 1) + "…" );
            Hashtable<ItemSet, Integer> tempFrequentItemSets = generateFrequentItemSets( supportThreshold, transactions, frequentItemSets );

            for (ItemSet item : frequentItemSets.keySet()) {
                itemSets.add(item);
            }

            if(tempFrequentItemSets.size() > 0) {
                frequentItemSets = tempFrequentItemSets ;
            }
            System.out.println( " found " + frequentItemSets.size() );
        }


        // TODO: create association rules from the frequent itemsets

        // TODO: return something useful
        return itemSets;
    }

    private static Hashtable<ItemSet, Integer> generateFrequentItemSets( int supportThreshold, int[][] transactions,
                    Hashtable<ItemSet, Integer> lowerLevelItemSets ) {

        //This calculation seems erroneous - Uhh... why? What the F was I thinking?
        Hashtable<ItemSet, Integer> frequentItemSetCandidates = new Hashtable<>();

        for (ItemSet item: lowerLevelItemSets.keySet()) {
            for (ItemSet item2: lowerLevelItemSets.keySet()) {
                if (!item.equals(item2)) {
                    frequentItemSetCandidates.put(joinSets(item, item2),0);
                }
            }
        }

        //TODO: Prune itemsets.

        //check the support for all candidates and add only those
        Hashtable<ItemSet, Integer> frequentItemSets = new Hashtable<>();
        for (ItemSet item: frequentItemSetCandidates.keySet()) {

            if(countSupport(item.set, transactions) >= supportThreshold) {
                frequentItemSets.put(item, frequentItemSetCandidates.get(item));
            }
        }

        return frequentItemSets;
    }

    private static ItemSet joinSets( ItemSet first, ItemSet second ) {
        int[] items = new int[first.set.length+1];
        for (int i = 0; i < first.set.length-1; i++){
            if(first.set[i] == second.set[i]) {
                items[i] = first.set[i];
            }
            else {
                return null;
            }
        }
        if(first.set[first.set.length-1] < second.set[second.set.length-1]) {
            items[items.length-2] = first.set[second.set.length-1];
            items[items.length-1] = second.set[second.set.length-1];
        } else {
            items[items.length-2] = second.set[second.set.length-1];
            items[items.length-1] = first.set[second.set.length-1];
        }
        return new ItemSet(items);
    }

    private static Hashtable<ItemSet, Integer> generateFrequentItemSetsLevel1( int[][] transactions, int supportThreshold ) {
        Hashtable<ItemSet, Integer> hashtable = new Hashtable<>();
        for(int i = 0; i < transactions.length; i++) {
            for(int j = 0; j < transactions[i].length; j++) {
                ItemSet itemSet = new ItemSet(new int[] {transactions[i][j]});
                if(!hashtable.containsKey(itemSet)) {
                    hashtable.put(itemSet,0);
                }
                hashtable.put(itemSet, hashtable.get(itemSet)+1);
            }
        }

        //TODO: Prune itemsets???

        Hashtable<ItemSet, Integer> frequentItemSets = new Hashtable<>();
        for (ItemSet item: hashtable.keySet()) {
            if(hashtable.get(item) >= supportThreshold) {
                frequentItemSets.put(item, hashtable.get(item));
            }
        }

        return frequentItemSets;
    }

    private static int countSupport( int[] itemSet, int[][] transactions ) {
        // Assumes that items in ItemSets and transactions are both unique
        int supportCount = 0;
        for(int i = 0; i < transactions.length; i++ ) {
            int matchedItemsetCount = 0;
            for (int k = 0; k < transactions[i].length; k++){
                for (int j = 0; j < itemSet.length; j++) {
                    if (transactions[i][k] == itemSet[j]) {
                        matchedItemsetCount ++;
                    }
                }
                if (matchedItemsetCount == itemSet.length) {
                    supportCount++;
                    break;
                }
            }
        }
        return supportCount;
    }

}
