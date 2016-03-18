using System;
using System.Collections.Generic;
using System.Linq;

namespace DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Apriori.Attribute
{
    public class Apriori
    {
        public static List<ItemSet> AprioriAlg(Entities.Attributes.Abstract.Attribute[][] transactions, int supportThreshold)
        {
            if (transactions == null) throw new ArgumentNullException(nameof(transactions));

            var frequentItemSets = GenerateFrequentItemSetsLevel1(transactions, supportThreshold);
            var itemSets = new Dictionary<ItemSet, int>(frequentItemSets);

            //Continues untill there is one or fewer itemsets left.
            for (var k = 1; frequentItemSets.Count > 0; k++)
            {
                frequentItemSets = GenerateFrequentItemSets(supportThreshold, transactions, frequentItemSets);

                foreach (var item in frequentItemSets.Keys)
                {
                    itemSets.Add(item, frequentItemSets[item]);
                }
            }

            var frequentItemSetsList = new List<ItemSet>();

            foreach(var item in itemSets)
            {
                item.Key.Support = item.Value;
                frequentItemSetsList.Add(item.Key);
            }

            return frequentItemSetsList;
        }

        private static Dictionary<ItemSet, int> GenerateFrequentItemSets(int supportThreshold, Entities.Attributes.Abstract.Attribute[][] transactions,
                Dictionary<ItemSet, int> lowerLevelItemSets)
        {
            //Compute the new candidate sets from the lower level frequent item sets.
            var frequentItemSetCandidates = new Dictionary<ItemSet, int>();

            var setCandidates = frequentItemSetCandidates;
            foreach (var itemSet in from item in lowerLevelItemSets.Keys
                                    let candidates = setCandidates from itemSet in 
                                                                       lowerLevelItemSets.Keys
                                                                       .Where(item2 => !item.Equals(item2))
                                                                       .Select(item2 => JoinSets(item, item2))
                                                                       .Where(itemSet => itemSet != null && !candidates.ContainsKey(itemSet))
                                    select itemSet)
            {
                frequentItemSetCandidates.Add(itemSet, 0);
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
            if (lowerLevelItemSets == null) throw new ArgumentNullException(nameof(lowerLevelItemSets));

            //Incase of errors, look here.
            //Pruning itemsets according to the apriori property.
            var frequentCandidateItemSetsPruned = new Dictionary<ItemSet, int>();
            foreach (var item in frequentItemSetCandidates)
            {
                var itemSetCandidateSubset = new List<ItemSet>();
                for (var i = 0; i < item.Key.Set.Length - 1; i++)
                {
                    itemSetCandidateSubset.Add(new ItemSet { Set = item.Key.Set.Where(x => !x.Equals(item.Key.Set[i])).ToArray() });
                }

                var keepSet = true;

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
        private static Dictionary<ItemSet, int> GenerateFrequentItemSetsLevel1(Entities.Attributes.Abstract.Attribute[][] transactions, int supportThreshold)
        {
            var dictionary = new Dictionary<ItemSet, int>();
            foreach (var t in transactions)
            {
                foreach (var itemSet in t.Select(t1 => new ItemSet {Set = new[] {t1}}))
                {
                    if (!dictionary.ContainsKey(itemSet))
                    {
                        dictionary.Add(itemSet, 0);
                    }
                    dictionary[itemSet] += 1;
                }
            }

            return dictionary.Keys.Where(item => dictionary[item] >= supportThreshold).ToDictionary(item => item, item => dictionary[item]);
        }

        //Joins itemsets
        private static ItemSet JoinSets(ItemSet first, ItemSet second)
        {
            var items = new Entities.Attributes.Abstract.Attribute[first.Set.Length + 1];
            for (var i = 0; i < first.Set.Length - 1; i++)
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
            if (string.Compare(first.Set[first.Set.Length - 1].ToString(), second.Set[second.Set.Length - 1].ToString(), StringComparison.Ordinal) < 0)
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
        private static int CountSupport(Entities.Attributes.Abstract.Attribute[] itemSet, IEnumerable<Entities.Attributes.Abstract.Attribute[]> transactions)
        {
            if (itemSet == null) throw new ArgumentNullException(nameof(itemSet));
            // Assumes that items in ItemSets and transactions are both unique
            var supportCount = 0;
            foreach (var t1 in transactions)
            {
                var matchedItemsetCount = 0;
                foreach (var t2 in t1)
                {
                    matchedItemsetCount += itemSet.Count(t => t2.ToString().Equals(t.ToString()));
                    if (matchedItemsetCount != itemSet.Length) continue;
                    supportCount++;
                    break;
                }
            }
            return supportCount;
        }
    }
}

