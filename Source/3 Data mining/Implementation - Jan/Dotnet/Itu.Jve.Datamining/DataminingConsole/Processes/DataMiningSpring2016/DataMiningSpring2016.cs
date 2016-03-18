using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using DataminingConsole.Processes.DataMiningSpring2016.Common;
using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes;
using DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes.Abstract;
using DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Clustering;
using DataminingConsole.Processes.DataMiningSpring2016.Preprocessing;
using DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Apriori;
using DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Apriori.Attribute;
using Attribute = DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes.Abstract.Attribute;

namespace DataminingConsole.Processes.DataMiningSpring2016
{
    public class DataMiningSpring2016 : IDataminingProcesses
    {
        #region Configuration
        private readonly string _csvPath;
        private readonly List<string> _csvAttributeNames;
        private readonly DataCleaningHandler _dataCleaningHandler;
        private readonly DataTransformationHandler _dataTransformationHandler;

        private readonly Func<DataTuple, bool> _ageNoisePredicate;

        private const int KMeanClusters = 3;
        private const int SupportThreshold = 5;


        public DataMiningSpring2016()
        {
            _csvAttributeNames = new List<string>()
            {
                Constant.CsvAttributeName.Age,
                Constant.CsvAttributeName.Degree,
                Constant.CsvAttributeName.GameFrequency,
                Constant.CsvAttributeName.PlayedGames,
                Constant.CsvAttributeName.FavoriteGame,
            }; ;
            _csvPath = @"Data\DataMining 2016 (Responses).csv";
            _dataCleaningHandler = new DataCleaningHandler();
            _dataTransformationHandler = new DataTransformationHandler();

            _ageNoisePredicate = x => x.Age.MissingValue || x.Age.Value < 10 || x.Age.Value > 70;
        }

        #endregion

        /// <summary>
        /// Interface endpoint. Call to start data process.
        /// </summary>
        public void Run()
        {
            var data = DataSelection();
            var dataset = PreProcessing(data);
            AprioriCalculate(dataset);
            ClusteringCalculate(dataset);
        }

        /// <summary>
        /// Load in data from dataselection and remove unused attributes.
        /// </summary>
        /// <returns></returns>
        public List<List<string>> DataSelection()
        {
            // Load in Csv
            var result = CsvFileReader.ReadDataFile(_csvPath);

            // Split attributes from tuples
            var attributeList = result.First(x => true);
            var cvsDataset = result.Skip(1).ToList();

            // Reduce attribute domain
            return cvsDataset.SelectAttributes(attributeList, _csvAttributeNames);
        }

        /// <summary>
        /// Normalize data and map to C# class.
        /// </summary>
        /// <param name="cvsDataset">Csv dataset to preprocess</param>
        /// <returns>List of DataTuples.</returns>
        public List<DataTuple> PreProcessing(List<List<string>> cvsDataset)
        {
            // Create attributeIndex
            var attributeIndex = _csvAttributeNames
                .Select((x, i) => new { key = x, value = i })
                .ToDictionary(x => DataMappers.AttributeTypeMapper(x.key), x => x.value);

            // Clean each tuple.
            cvsDataset.MapColumn(attributeIndex, AttributeType.Degree, _dataCleaningHandler.StringCleaner);
            cvsDataset.MapColumn(attributeIndex, AttributeType.FavoriteGame, _dataCleaningHandler.StringCleaner);
            cvsDataset.MapColumn(attributeIndex, AttributeType.GameFrequency, _dataCleaningHandler.StringCleaner);
            cvsDataset.MapColumn(attributeIndex, AttributeType.PlayedGames, _dataCleaningHandler.StringCleaner);

            // Convert strings to complex object.
            var dataset = cvsDataset
                .Select(x => _dataTransformationHandler.TransformTuple(x, attributeIndex)).ToList();

            // Remove noise from dataset. atm. only age.
            dataset = dataset.RemoveAgeNoise(_ageNoisePredicate, NoiseHandler.NoiseMethod.Median);

            // Normalize input.
            dataset = dataset.MinMaxNormalizeDataTuple();
            return dataset;
        }

        /// <summary>
        /// Start the clustering process.
        /// </summary>
        /// <param name="dataSet">Dataset to perform process on</param>
        public void ClusteringCalculate(List<DataTuple> dataSet)
        {
            var clusters = KMeans.KMeansPartition(KMeanClusters, dataSet);
            clusters.ForEach(ClusterAnalysis);
        }

        /// <summary>
        /// Start the apriori process.
        /// </summary>
        /// <param name="dataSet">Dataset to perform process on</param>
        public void AprioriCalculate(List<DataTuple> dataSet)
        {
            var attributes = dataSet.Select(x => new Attribute[] { x.Age, x.GameFrequency, x.Degree, x.FavoriteGame }).ToArray();
            var patterns = Apriori.AprioriAlg(attributes, SupportThreshold);
            AprioriAnalysis(patterns);
        }

        /// <summary>
        /// Helper method for data analysis
        /// </summary>
        /// <param name="patterns"></param>
        private static void AprioriAnalysis(IEnumerable<ItemSet> patterns)
        {
            patterns.Where(x => x.Set.Length > 1).ToList().ForEach(x => Debug.WriteLine($"patterns larger than 1: {x}"));
        }

        /// <summary>
        /// Helper method for data analysis
        /// </summary>
        /// <param name="cluster"></param>
        private static void ClusterAnalysis(KMeanCluster cluster)
        {
            var never = cluster.ClusterMembers.Count(x => x.GameFrequency.Value == GameFrequencyType.Never);
            var lessThan5HoursAWeek = cluster.ClusterMembers.Count(x => x.GameFrequency.Value == GameFrequencyType.LessThan5HoursAWeek);
            var lessThan10HoursAWeek = cluster.ClusterMembers.Count(x => x.GameFrequency.Value == GameFrequencyType.LessThan10HoursAWeek);
            var lessThan15HoursAWeek = cluster.ClusterMembers.Count(x => x.GameFrequency.Value == GameFrequencyType.LessThan15HoursAWeek);
            var lessThan20HoursAWeek = cluster.ClusterMembers.Count(x => x.GameFrequency.Value == GameFrequencyType.LessThan20HoursAWeek);
            var moreThan20HoursAWeek = cluster.ClusterMembers.Count(x => x.GameFrequency.Value == GameFrequencyType.MoreThan20HoursAWeek);
            Debug.WriteLine($"Never: {never}");
            Debug.WriteLine($"LessThan5HoursAWeek: {lessThan5HoursAWeek}");
            Debug.WriteLine($"LessThan10HoursAWeek: {lessThan10HoursAWeek}");
            Debug.WriteLine($"LessThan15HoursAWeek: {lessThan15HoursAWeek}");
            Debug.WriteLine($"LessThan20HoursAWeek: {lessThan20HoursAWeek}");
            Debug.WriteLine($"MoreThan20HoursAWeek: {moreThan20HoursAWeek}");
            Debug.WriteLine(cluster);

            var ageMean = cluster.ClusterMembers.Sum(x => x.Age.Value) / cluster.ClusterMembers.Count;
            Debug.WriteLine($"ageMean: {ageMean}");

        }
    }
}