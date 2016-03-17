using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Apriori
{
    class Apriori
    {
        static int[][] TRANSACTIONS = new int[][] { new int[]{ 1, 2, 3, 4, 5 }, new int[]{ 1, 3, 5 },new int[] { 2, 3, 5 }, new int[]{ 1, 5 },
            new int[] { 1, 3, 4 }, new int[]{ 2, 3, 5 }, new int[] { 2, 3, 5 }, new int[]{ 3, 4, 5 },new int[] { 4, 5 }, new int[]{ 2 },
            new int[]{ 2, 3 },new int[] { 2, 3, 4 },new int[] { 3, 4, 5 } };

        static int[][] BOOK_TRANSACTIONS = new int[][] { new int[]{ 1, 2, 5 },new int[] {2, 4}, new int[]{ 2, 3 },new int[] { 1, 2, 4 },
            new int[] { 1, 3 },new int[] { 2, 3 },new int[] { 1, 3 },new int[]{ 1, 2, 3, 5 }, new int[]{ 1, 2, 3 }};

        public static List<ItemSet> apriori(int[][] transactions, int supportThreshold)
        {
            int k = 1;
            Dictionary<ItemSet, int> frequentItemSets = generateFrequentItemSetsLevel1(transactions, supportThreshold);
            var itemSets = new Dictionary<ItemSet>();

            //Continues untill there is one or fewer itemsets left.
            while (frequentItemSets.Count > 1)
            {
                k++;
                Console.WriteLine("Finding frequent itemsets of length " + (k + 1) + "…");
                var tempFrequentItemSets = generateFrequentItemSets(supportThreshold, transactions, frequentItemSets);

                foreach (ItemSet item in  frequentItemSets.Keys)
                {
                    itemSets.Add(item, frequentItemSets[item]);
                }

                if (tempFrequentItemSets.Count() > 0)
                {
                    frequentItemSets = tempFrequentItemSets;
                }
                Console.WriteLine(" found " + frequentItemSets.Count());
            }


            // TODO: create association rules from the frequent itemsets
            // These should be created using page 254.
            foreach(var item in itemSets) {
                
            }
            
            // TODO: return something useful
            
            return itemSets;
        }

        private static Dictionary<ItemSet, int> generateFrequentItemSets(int supportThreshold, int[][] transactions,
                Dictionary<ItemSet, int> lowerLevelItemSets)
        {
            var frequentItemSetCandidates = new Dictionary<ItemSet, int>();

            foreach (var item in lowerLevelItemSets.Keys)
            {
                foreach (var item2 in lowerLevelItemSets.Keys)
                {
                    if (!item.Equals(item2))
                    {
                        frequentItemSetCandidates.Add(joinSets(item, item2), 0);
                    }
                }
            }
            
            //Pruning itemsets according to the apriori property.
            var frequentCandidateItemSetsPruned = new Dictionary<ItemSet, int>();
            foreach(var item in frequentItemSetCandidates)
            {
                List<ItemSet> itemSetCandidateSubset = new List<ItemSet>();
                for(int i = 0; i < item.Set.Length-1; i++)
                {
                    var items = new int[]();
                    for(int j = 0; j < item.Set.Length-1; i++){
                        if(i != j) {
                            items.Add(item[i]);
                        }
                    }
                    itemSetCandidateSubset.Add(new ItemSet{ Set = items });
                }
                
                foreach(var candidateSet in itemSetCandidateSubset)
                {
                    var keepSet = true;
                    if(!lowerLevelItemSets.Contains(candidateSet)) {
                        keepSet = false;
                    }
                }
                if(keepSet) frequentCandidateItemSetsPruned.Add(itemSet, 0)
            }

            //check the support for all candidates and add only those
            var frequentItemSets = new Dictionary<ItemSet, int>();
            foreach (ItemSet item in frequentItemSetCandidates.Keys)
            {

                if (countSupport(item.Set, transactions) >= supportThreshold)
                {
                    frequentItemSets.Add(item, frequentItemSetCandidates[item]);
                }
            }
            return frequentItemSets;
        }

        //Creates a set of itemsets that is above a given support threshold
        private static Dictionary<ItemSet, int> generateFrequentItemSetsLevel1(int[][] transactions, int supportThreshold)
        {
            var dictionary = new Dictionary<ItemSet, int>();
            for (int i = 0; i < transactions.Length; i++)
            {
                for (int j = 0; j < transactions[i].Length; j++)
                {
                    ItemSet itemSet = new ItemSet { Set = new int[] { transactions[i][j] } };
                    if (!dictionary.ContainsKey(itemSet))
                    {
                        dictionary.Add(itemSet, 0);
                    }
                    dictionary.Add(itemSet, dictionary[itemSet] +1);
                }
            }

            var frequentItemSets = new Dictionary<ItemSet, int>();
            foreach (ItemSet item in dictionary.Keys)
            {
                
                if (dictionary[item] >= supportThreshold)
                {
                    frequentItemSets.Add(item, dictionary[item]);
                }
            }
            return frequentItemSets;
        }

        //Joins itemsets
        private static ItemSet joinSets(ItemSet first, ItemSet second)
        {
            int[] items = new int[first.Set.Length + 1];
            for (int i = 0; i < first.Set.Length - 1; i++)
            {
                if (first.Set[i] == second.Set[i])
                {
                    items[i] = first.Set[i];
                }
                else
                {
                    return null;
                }
            }
            if (first.Set[first.Set.Length - 1] < second.Set[second.Set.Length - 1])
            {
                items[items.Length - 2] = first.Set[second.Set.Length - 1];
                items[items.Length - 1] = second.Set[second.Set.Length - 1];
            }
            else
            {
                items[items.Length - 2] = second.Set[second.Set.Length - 1];
                items[items.Length - 1] = first.Set[second.Set.Length - 1];
            }
            return new ItemSet { Set = items };
        }

        //Counts the support for an itemset in a given set of transactions
        private static int countSupport(int[] itemSet, int[][] transactions)
        {
            // Assumes that items in ItemSets and transactions are both unique
            int supportCount = 0;
            for (int i = 0; i < transactions.Length; i++)
            {
                int matchedItemsetCount = 0;
                for (int k = 0; k < transactions[i].Length; k++)
                {
                    for (int j = 0; j < itemSet.Length; j++)
                    {
                        if (transactions[i][k] == itemSet[j])
                        {
                            matchedItemsetCount++;
                        }
                    }
                    if (matchedItemsetCount == itemSet.Length)
                    {
                        supportCount++;
                        break;
                    }
                }
            }
            return supportCount;
        }
    }
}
