using DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes;
using System;
using System.Collections.Generic;
using System.Linq;

namespace DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Apriori
{
    public class AprioriForInts
    {
        public static int[][] TRANSACTIONS = new int[][] { new int[]{ 1, 2, 3, 4, 5 }, new int[]{ 1, 3, 5 },new int[] { 2, 3, 5 }, new int[]{ 1, 5 },
            new int[] { 1, 3, 4 }, new int[]{ 2, 3, 5 }, new int[] { 2, 3, 5 }, new int[]{ 3, 4, 5 },new int[] { 4, 5 }, new int[]{ 2 },
            new int[]{ 2, 3 },new int[] { 2, 3, 4 },new int[] { 3, 4, 5 } };

        static int[][] BOOK_TRANSACTIONS = new int[][] { new int[]{ 1, 2, 5 },new int[] {2, 4}, new int[]{ 2, 3 },new int[] { 1, 2, 4 },
            new int[] { 1, 3 },new int[] { 2, 3 },new int[] { 1, 3 },new int[]{ 1, 2, 3, 5 }, new int[]{ 1, 2, 3 }};

        static Entities.Attribute[][] ATTRIBUTE_TRANSACTIONS = { new Entities.Attribute[] { new AgeAttribute { } } };

        public static List<ItemSetForInts> AprioriAlg(int[][] transactions, int supportThreshold)
        {
            Dictionary<ItemSetForInts, int> frequentItemSets = GenerateFrequentItemSetsLevel1(transactions, supportThreshold);
            var itemSets = new Dictionary<ItemSetForInts, int>(frequentItemSets);

            //Continues untill there is one or fewer itemsets left.
            for (int k = 1;  frequentItemSets.Count > 0; k++)
            {
                Console.WriteLine("Finding frequent itemsets of length " + (k + 1) + "…");
                frequentItemSets = GenerateFrequentItemSets(supportThreshold, transactions, frequentItemSets);

                foreach (ItemSetForInts item in frequentItemSets.Keys)
                {
                    itemSets.Add(item, frequentItemSets[item]);
                }

                Console.WriteLine(" found " + frequentItemSets.Count());
            }

            // Returning something useful
            // If this method were to be used for creation of association rules, then it should merely return the dictionary instead. 
            return itemSets.Keys.ToList();
        }

        private static Dictionary<ItemSetForInts, int> GenerateFrequentItemSets(int supportThreshold, int[][] transactions,
                Dictionary<ItemSetForInts, int> lowerLevelItemSets)
        {
            //Compute the new candidate sets from the lower level frequent item sets.
            var frequentItemSetCandidates = new Dictionary<ItemSetForInts, int>();

            foreach (var item in lowerLevelItemSets.Keys)
            {
                foreach (var item2 in lowerLevelItemSets.Keys)
                {
                    if (!item.Equals(item2))
                    {
                        var itemSet = JoinSets(item, item2);
                        if(itemSet != null && !frequentItemSetCandidates.ContainsKey(itemSet))
                        {
                            frequentItemSetCandidates.Add(itemSet, 0);
                        }
                        
                    }
                }
            }
            
            //Incase of errors, look here.
            //Pruning itemsets according to the apriori property.
            var frequentCandidateItemSetsPruned = new Dictionary<ItemSetForInts, int>();
            foreach(var item in frequentItemSetCandidates)
            {
                List<ItemSetForInts> itemSetCandidateSubset = new List<ItemSetForInts>();
                for(int i = 0; i < item.Key.Set.Length-1; i++)
                {
                    itemSetCandidateSubset.Add(new ItemSetForInts { Set = item.Key.Set.Where(x => !x.Equals(item.Key.Set[i])).ToArray() });
                    
                    /*var items = new int[item.Key.Set.Length - 2];
                    for(int j = 0; j < item.Key.Set.Length-1; i++){
                        if(i != j) {
                            items.(item.Key.Set[i]);
                        }
                    }
                    itemSetCandidateSubset.Add(new ItemSet{ Set = items });*/
                }

                bool keepSet = true;

                foreach(var candidateSet in itemSetCandidateSubset)
                {
                    if (!lowerLevelItemSets.ContainsKey(candidateSet)) {
                        keepSet = false;
                    }
                }
                if (keepSet) frequentCandidateItemSetsPruned.Add(item.Key, 0);
            }

            //Check the support for all candidates and add only those that are above the threshold
            var frequentItemSets = new Dictionary<ItemSetForInts, int>();
            foreach (ItemSetForInts item in frequentItemSetCandidates.Keys)
            {
                var support = CountSupport(item.Set, transactions);
                if (support >= supportThreshold)
                {
                    frequentItemSets.Add(item, support);
                }
            }
            return frequentItemSets;
        }

        //Creates a set of itemsets that is above a given support threshold
        private static Dictionary<ItemSetForInts, int> GenerateFrequentItemSetsLevel1(int[][] transactions, int supportThreshold)
        {
            var dictionary = new Dictionary<ItemSetForInts, int>();
            for (int i = 0; i < transactions.Length; i++)
            {
                for (int j = 0; j < transactions[i].Length; j++)
                {
                    ItemSetForInts itemSet = new ItemSetForInts { Set = new int[] { transactions[i][j] } };
                    if (!dictionary.ContainsKey(itemSet))
                    {
                        dictionary.Add(itemSet, 0);
                    }
                    //dictionary.Add(itemSet, dictionary[itemSet] +1);
                    dictionary[itemSet] += 1;
                }
            }

            var frequentItemSets = new Dictionary<ItemSetForInts, int>();
            foreach (ItemSetForInts item in dictionary.Keys)
            {
                
                if (dictionary[item] >= supportThreshold)
                {
                    frequentItemSets.Add(item, dictionary[item]);
                }
            }
            return frequentItemSets;
        }

        //Joins itemsets
        private static ItemSetForInts JoinSets(ItemSetForInts first, ItemSetForInts second)
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
            return new ItemSetForInts { Set = items };
        }

        //Counts the support for an itemset in a given set of transactions
        private static int CountSupport(int[] itemSet, int[][] transactions)
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
