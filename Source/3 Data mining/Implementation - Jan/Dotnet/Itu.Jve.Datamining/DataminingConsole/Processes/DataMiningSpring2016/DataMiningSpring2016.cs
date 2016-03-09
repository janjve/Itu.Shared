using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using DataminingConsole.Processes.DataMiningSpring2016.Common;
using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using DataminingConsole.Processes.DataMiningSpring2016.Entities.Age;
using DataminingConsole.Processes.DataMiningSpring2016.Preprocessing;

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
        private readonly Func<DataTuple, bool> _degreeNoisePredicate;
        private readonly Func<DataTuple, bool> _gameFrequencyNoisePredicate;
        private readonly Func<DataTuple, bool> _favoriteGameNoisePredicate;
        private readonly Func<DataTuple, bool> _playedGameNoisePredicate;


        public DataMiningSpring2016()
        {
            _csvAttributeNames = new List<string>()
            {
                Constant.CsvAttributeName.Age,
                Constant.CsvAttributeName.Degree,
                Constant.CsvAttributeName.GameFrequency,
                Constant.CsvAttributeName.FavoriteGame,
                Constant.CsvAttributeName.PlayedGames,
            }; ;
            _csvPath = @"Data\DataMining 2016 (Responses).csv";
            _dataCleaningHandler = new DataCleaningHandler();
            _dataTransformationHandler = new DataTransformationHandler();

            _ageNoisePredicate = x => /*x.MissingValue ||*/ x.Age.Value < 10 || x.Age.Value > 70;
            _degreeNoisePredicate = x => /*x.MissingValue ||*/ false;
            _gameFrequencyNoisePredicate = x => false;
            _favoriteGameNoisePredicate = x => false;
            _playedGameNoisePredicate = x => false;
        }

        #endregion

        public void Start()
        {
            // Part 1
            // Load in Csv
            var result = CsvFileReader.ReadDataFile(_csvPath);

            // Part 2
            // Split attributes from tuples
            var attributeList = result.First(x => true);
            var dataset = result.Skip(1).ToList();

            // Reduce attribute domain
            dataset = dataset.SelectAttributes(attributeList, _csvAttributeNames);

            // Remove \" from all string values????? - REMEMBER TO UPDATE AttributeTypeMapper

            // Create attributeIndex
            var attributeIndex = _csvAttributeNames
                .Select((x, i) => new { key = x, value = i })
                .ToDictionary(x => DataMappers.AttributeTypeMapper(x.key), x => x.value);

            Logger.Log(dataset, "Attributes selected");

            dataset.MapColumn(attributeIndex, AttributeType.Age, _dataCleaningHandler.AgeCleaner);

            var transformedDataset = dataset
                .Select(x => _dataTransformationHandler.TransformTuple(x, attributeIndex)).ToList();

            var ageNoiseRemovedDataset = transformedDataset.RemoveAgeNoise(_ageNoisePredicate, NoiseHandler.NoiseMethod.Median);
            //... noise from attributes


            var normalizedDataset = ageNoiseRemovedDataset.MinMaxNormalizeDataTuple();
            var ageSet = transformedDataset.Select(x => x.Age).ToList();

            transformedDataset.OrderBy(x => x.Age).ToList().ForEach(x => Debug.WriteLine(x));
        }

        public void PatternDiscovery(List<DataTuple> dataTuple)
        {

        }
    }
}


//var mean = ageSet.Mean();
//var median = ageSet.Median();
//var mode = ageSet.Mode();
//var midrange = ageSet.Midrange();
//var range = ageSet.Range();
//var twoQuantiles = ageSet.Quantile(2);
//var iqr = ageSet.InterQuartileRange();
//var variance = ageSet.Variance();
//var standardDeviation = ageSet.StandardDeviation();
//var fiveNumberSummary = ageSet.FiveNumberSummary();

//Debug.WriteLine($"Age mean : {mean}");
//Debug.WriteLine($"Age median : {median}");
//Debug.WriteLine($"Age mode : {mode}");
//Debug.WriteLine($"Age midrange : {midrange}");
//Debug.WriteLine($"Age range : {range}");
//Debug.WriteLine($"Age 2-quantiles : {twoQuantiles[0]}");
//Debug.WriteLine($"Age iqr : {iqr}");
//Debug.WriteLine($"Age variance : {variance}");
//Debug.WriteLine($"Age standardDeviation : {standardDeviation}");
//Debug.WriteLine($"Age fiveNumberSummary : {fiveNumberSummary}");