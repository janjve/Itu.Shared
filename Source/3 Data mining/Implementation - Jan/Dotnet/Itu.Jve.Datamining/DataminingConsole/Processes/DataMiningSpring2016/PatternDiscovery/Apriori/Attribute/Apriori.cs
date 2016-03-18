using DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes;
using System;
using System.Collections.Generic;
using System.Linq;

namespace DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Apriori
{
    class Apriori
    {


        public static Entities.Attribute[][] ATTRIBUTE_TRANSACTIONS = {
            new Entities.Attribute[] { new AgeAttribute { Value = 1 }, new AgeAttribute { Value = 2 }, new AgeAttribute { Value = 3 }, new AgeAttribute { Value = 4 }, new AgeAttribute { Value = 5 } },
            new Entities.Attribute[] { new AgeAttribute { Value = 1 }, new AgeAttribute { Value = 2 }, new AgeAttribute { Value = 3 }, new AgeAttribute { Value = 4 }, new AgeAttribute { Value = 5 } },
            new Entities.Attribute[] { new AgeAttribute { Value = 2 }, new AgeAttribute { Value = 4 } },
            new Entities.Attribute[] { new AgeAttribute { Value = 1 }, new AgeAttribute { Value = 4 } },
            new Entities.Attribute[] { new AgeAttribute { Value = 3 }, new AgeAttribute { Value = 5 } },
            new Entities.Attribute[] { new AgeAttribute { Value = 3 }, new AgeAttribute { Value = 5 } },
            new Entities.Attribute[] { new AgeAttribute { Value = 2 }, new AgeAttribute { Value = 3 }, new AgeAttribute { Value = 4 } },
            new Entities.Attribute[] { new AgeAttribute { Value = 1 }, new AgeAttribute { Value = 2 }, new AgeAttribute { Value = 3 } }
        };

        public static List<ItemSet> AprioriAlg(Entities.Attribute[][] transactions, int supportThreshold)
        {
            Dictionary<ItemSet, int> frequentItemSets = GenerateFrequentItemSetsLevel1(transactions, supportThreshold);
            var itemSets = new Dictionary<ItemSet, int>(frequentItemSets);

            //Continues untill there is one or fewer itemsets left.
            for (int k = 1; frequentItemSets.Count > 0; k++)
            {
                Console.WriteLine("Finding frequent itemsets of length " + (k + 1) + "…");
                frequentItemSets = GenerateFrequentItemSets(supportThreshold, transactions, frequentItemSets);

                foreach (ItemSet item in frequentItemSets.Keys)
                {
                    itemSets.Add(item, frequentItemSets[item]);
                }

                Console.WriteLine(" found " + frequentItemSets.Count());
            }

            // Returning something useful
            // If this method were to be used for creation of association rules, then it should merely return the dictionary instead.
            // Should it always do this!? All information about support is lost this way... 
            var frequentItemSetsList = new List<ItemSet>();

            foreach(var item in itemSets)
            {
                item.Key.Support = item.Value;
                frequentItemSetsList.Add(item.Key);
            }

            return frequentItemSetsList;
        }

        private static Dictionary<ItemSet, int> GenerateFrequentItemSets(int supportThreshold, Entities.Attribute[][] transactions,
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
                        if (itemSet != null && !frequentItemSetCandidates.ContainsKey(itemSet))
                        {
                            frequentItemSetCandidates.Add(itemSet, 0);
                        }
                    }
                }
            }

            frequentItemSetCandidates = PruneItemsets(lowerLevelItemSets, frequentItemSetCandidates);

            //Check the support for all candidates and add only those that are above the threshold
            var frequentItemSets = new Dictionary<ItemSet, int>();
            foreach (var item in frequentItemSetCandidates.Keys)
            {
                var support = CountSupport(item.Set, transactions);
                if (support >= supportThreshold)
                {
                    frequentItemSets.Add(item, support);
                }
            }
            return frequentItemSets;
        }

        private static Dictionary<ItemSet, int> PruneItemsets(Dictionary<ItemSet, int> lowerLevelItemSets, Dictionary<ItemSet, int> frequentItemSetCandidates)
        {
            //Incase of errors, look here.
            //Pruning itemsets according to the apriori property.
            var frequentCandidateItemSetsPruned = new Dictionary<ItemSet, int>();
            foreach (var item in frequentItemSetCandidates)
            {
                var itemSetCandidateSubset = new List<ItemSet>();
                for (int i = 0; i < item.Key.Set.Length - 1; i++)
                {
                    itemSetCandidateSubset.Add(new ItemSet { Set = item.Key.Set.Where(x => !x.Equals(item.Key.Set[i])).ToArray() });
                }

                bool keepSet = true;

                foreach (var candidateSet in itemSetCandidateSubset)
                {
                    if (!lowerLevelItemSets.ContainsKey(candidateSet))
                    {
                        keepSet = false;
                    }
                }
                if (keepSet) frequentCandidateItemSetsPruned.Add(item.Key, 0);
            }
            return frequentCandidateItemSetsPruned;
        }

        //Creates a set of itemsets that is above a given support threshold
        private static Dictionary<ItemSet, int> GenerateFrequentItemSetsLevel1(Entities.Attribute[][] transactions, int supportThreshold)
        {
            var dictionary = new Dictionary<ItemSet, int>();
            for (int i = 0; i < transactions.Length; i++)
            {
                for (int j = 0; j < transactions[i].Length; j++)
                {
                    ItemSet itemSet = new ItemSet { Set = new Entities.Attribute[] { transactions[i][j] } };
                    var hashCode = itemSet.GetHashCode();
                    var doesObjectExist = dictionary.Keys.FirstOrDefault(x => x.Equals(itemSet));
                    if (!dictionary.ContainsKey(itemSet))
                    {
                        dictionary.Add(itemSet, 0);
                    }
                    dictionary[itemSet] += 1;
                }
            }

            var frequentItemSets = new Dictionary<ItemSet, int>();
            foreach (var item in dictionary.Keys)
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
            var items = new Entities.Attribute[first.Set.Length + 1];
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
            if (first.Set[first.Set.Length - 1].ToString().CompareTo(second.Set[second.Set.Length - 1].ToString()) < 0)
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
        private static int CountSupport(Entities.Attribute[] itemSet, Entities.Attribute[][] transactions)
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
                        if (transactions[i][k].ToString().Equals(itemSet[j].ToString()) )
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

