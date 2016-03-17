using DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes;
using System;
using System.Collections.Generic;
using System.Linq;

namespace DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Apriori
{
    public class Apriori
    {
        public static int[][] TRANSACTIONS = new int[][] { new int[]{ 1, 2, 3, 4, 5 }, new int[]{ 1, 3, 5 },new int[] { 2, 3, 5 }, new int[]{ 1, 5 },
            new int[] { 1, 3, 4 }, new int[]{ 2, 3, 5 }, new int[] { 2, 3, 5 }, new int[]{ 3, 4, 5 },new int[] { 4, 5 }, new int[]{ 2 },
            new int[]{ 2, 3 },new int[] { 2, 3, 4 },new int[] { 3, 4, 5 } };

        static int[][] BOOK_TRANSACTIONS = new int[][] { new int[]{ 1, 2, 5 },new int[] {2, 4}, new int[]{ 2, 3 },new int[] { 1, 2, 4 },
            new int[] { 1, 3 },new int[] { 2, 3 },new int[] { 1, 3 },new int[]{ 1, 2, 3, 5 }, new int[]{ 1, 2, 3 }};

        static Entities.Attribute[][] ATTRIBUTE_TRANSACTIONS = { new Entities.Attribute[] { new AgeAttribute { } } };

        public static List<AssociationRule> AprioriAlg(int[][] transactions, int supportThreshold)
        {
            int k = 1;
            Dictionary<ItemSet, int> frequentItemSets = GenerateFrequentItemSetsLevel1(transactions, supportThreshold);
            var itemSets = new Dictionary<ItemSet, int>();

            //Continues untill there is one or fewer itemsets left.
            while (frequentItemSets.Count > 1)
            {
                k++;
                Console.WriteLine("Finding frequent itemsets of length " + (k + 1) + "…");
                var tempFrequentItemSets = GenerateFrequentItemSets(supportThreshold, transactions, frequentItemSets);

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

            // Returning something useful
            return CreateAssociationRules(itemSets); ;
        }

        // Creating association rules from the frequent itemsets
        // This should problably be done somewhere else??
        private static List<AssociationRule> CreateAssociationRules(Dictionary<ItemSet, int> FrequentItemSets)
        {
            var AssociationRuleList = new List<AssociationRule>();

            //Creating all possible equivalence rules
            foreach (var item1 in FrequentItemSets)
            {
                foreach(var item2 in FrequentItemSets)
                {
                    if(item1.Equals(item2)) { break; } //ItemSets are equivalent
                    var AssociationRule = new AssociationRule { Premise = item1.Key, Conclusion = item2.Key };
                    var tempItemSet = new ItemSet { Set = item1.Key.Set.Union(item2.Key.Set).OrderBy(x => x).ToArray() };
                    int supportForUnion;
                    
                    //Test if this works properly, might only be tested for reference!!!
                    if (FrequentItemSets.TryGetValue(tempItemSet, out supportForUnion))
                    {
                        AssociationRule.Confidence = supportForUnion / item1.Value;
                        AssociationRuleList.Add(AssociationRule);
                    }
                }
            }
            return AssociationRuleList;
        }

        private static Dictionary<ItemSet, int> GenerateFrequentItemSets(int supportThreshold, int[][] transactions,
                Dictionary<ItemSet, int> lowerLevelItemSets)
        {
            //Compute the new candidate sets from the lower level frequent item sets.
            var frequentItemSetCandidates = new Dictionary<ItemSet, int>();

            foreach (var item in lowerLevelItemSets.Keys)
            {
                foreach (var item2 in lowerLevelItemSets.Keys)
                {
                    if (!item.Equals(item2))
                    {
                        var itemSet = JoinSets(item, item2);
                        if(!frequentItemSetCandidates.ContainsKey(itemSet))
                        {
                            frequentItemSetCandidates.Add(itemSet, 0);
                        }
                        
                    }
                }
            }
            
            //Incase of errors, look here.
            //Pruning itemsets according to the apriori property.
            var frequentCandidateItemSetsPruned = new Dictionary<ItemSet, int>();
            foreach(var item in frequentItemSetCandidates)
            {
                List<ItemSet> itemSetCandidateSubset = new List<ItemSet>();
                for(int i = 0; i < item.Key.Set.Length-1; i++)
                {
                    itemSetCandidateSubset.Add(new ItemSet { Set = item.Key.Set.Where(x => !x.Equals(item.Key.Set[i])).ToArray() });
                    
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
            var frequentItemSets = new Dictionary<ItemSet, int>();
            foreach (ItemSet item in frequentItemSetCandidates.Keys)
            {

                if (CountSupport(item.Set, transactions) >= supportThreshold)
                {
                    frequentItemSets.Add(item, frequentItemSetCandidates[item]);
                }
            }
            return frequentItemSets;
        }

        //Creates a set of itemsets that is above a given support threshold
        private static Dictionary<ItemSet, int> GenerateFrequentItemSetsLevel1(int[][] transactions, int supportThreshold)
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
                    //dictionary.Add(itemSet, dictionary[itemSet] +1);
                    dictionary[itemSet] += 1;
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
        private static ItemSet JoinSets(ItemSet first, ItemSet second)
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
