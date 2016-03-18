using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using DataminingConsole.Processes.DataMiningSpring2016.Common;
using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes;
using DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Clustering;
using DataminingConsole.Processes.DataMiningSpring2016.Preprocessing;
using DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Apriori;

namespace DataminingConsole.Processes.DataMiningSpring2016
{
    public class DataMiningSpring2016 : IDataminingProcesses
    {
        #region Configuration
        // Preprocessing
        private readonly string _csvPath;
        private readonly List<string> _csvAttributeNames;
        private readonly DataCleaningHandler _dataCleaningHandler;
        private readonly DataTransformationHandler _dataTransformationHandler;

        private readonly Func<DataTuple, bool> _ageNoisePredicate;
        private readonly Func<DataTuple, bool> _degreeNoisePredicate;
        private readonly Func<DataTuple, bool> _gameFrequencyNoisePredicate;
        private readonly Func<DataTuple, bool> _favoriteGameNoisePredicate;
        private readonly Func<DataTuple, bool> _playedGameNoisePredicate;

        // Pattern discovery
        private readonly int _kMeanClusters;

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
            _degreeNoisePredicate = x => /*x.MissingValue ||*/ false;
            _gameFrequencyNoisePredicate = x => false;
            _favoriteGameNoisePredicate = x => false;
            _playedGameNoisePredicate = x => false;

            // Pattern discovery
            _kMeanClusters = 3;

        }

        #endregion

        public void Run()
        {
            var data = DataSelection();
            var dataset = PreProcessing(data);
            PatternDiscovery(dataset);
            //var patterns = Apriori.AprioriAlg(Apriori.ATTRIBUTE_TRANSACTIONS, 2);
        }

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

        public List<DataTuple> PreProcessing(List<List<string>> cvsDataset)
        {
            // Create attributeIndex
            var attributeIndex = _csvAttributeNames
                .Select((x, i) => new { key = x, value = i })
                .ToDictionary(x => DataMappers.AttributeTypeMapper(x.key), x => x.value);

            cvsDataset.MapColumn(attributeIndex, AttributeType.Degree, _dataCleaningHandler.StringCleaner);
            cvsDataset.MapColumn(attributeIndex, AttributeType.FavoriteGame, _dataCleaningHandler.StringCleaner);
            cvsDataset.MapColumn(attributeIndex, AttributeType.GameFrequency, _dataCleaningHandler.StringCleaner);
            cvsDataset.MapColumn(attributeIndex, AttributeType.PlayedGames, _dataCleaningHandler.StringCleaner);

            var dataset = cvsDataset
                .Select(x => _dataTransformationHandler.TransformTuple(x, attributeIndex)).ToList();

            dataset = dataset.RemoveAgeNoise(_ageNoisePredicate, NoiseHandler.NoiseMethod.Median);
            //... noise from attributes

            dataset = dataset.MinMaxNormalizeDataTuple();

            return dataset;
        }

        public void PatternDiscovery(List<DataTuple> dataSet)
        {
            Debug.WriteLine("=======================================================================");
            dataSet.ForEach(x => Debug.WriteLine(x));
            Debug.WriteLine("=======================================================================");
            Debug.WriteLine("=======================================================================");

            var clusters = KMeans.KMeansPartition(_kMeanClusters, dataSet);
            clusters.ForEach(ClusterAnalysis);
        }

        private static void ClusterAnalysis(KMeanCluster cluster)
        {
            //var never = cluster.ClusterMembers.Count(x => x.GameFrequency.Value == GameFrequencyType.Never);
            //var lessThan5HoursAWeek = cluster.ClusterMembers.Count(x => x.GameFrequency.Value == GameFrequencyType.LessThan5HoursAWeek);
            //var lessThan10HoursAWeek = cluster.ClusterMembers.Count(x => x.GameFrequency.Value == GameFrequencyType.LessThan10HoursAWeek);
            //var lessThan15HoursAWeek = cluster.ClusterMembers.Count(x => x.GameFrequency.Value == GameFrequencyType.LessThan15HoursAWeek);
            //var lessThan20HoursAWeek = cluster.ClusterMembers.Count(x => x.GameFrequency.Value == GameFrequencyType.LessThan20HoursAWeek);
            //var moreThan20HoursAWeek = cluster.ClusterMembers.Count(x => x.GameFrequency.Value == GameFrequencyType.MoreThan20HoursAWeek);
            //Debug.WriteLine($"Never: {never}");
            //Debug.WriteLine($"LessThan5HoursAWeek: {lessThan5HoursAWeek}");
            //Debug.WriteLine($"LessThan10HoursAWeek: {lessThan10HoursAWeek}");
            //Debug.WriteLine($"LessThan15HoursAWeek: {lessThan15HoursAWeek}");
            //Debug.WriteLine($"LessThan20HoursAWeek: {lessThan20HoursAWeek}");
            //Debug.WriteLine($"MoreThan20HoursAWeek: {moreThan20HoursAWeek}");
            Debug.WriteLine(cluster);

            var ageMean = cluster.ClusterMembers.Sum(x => x.Age.Value) / cluster.ClusterMembers.Count;
            Debug.WriteLine($"ageMean: {ageMean}");

        }
    }
}