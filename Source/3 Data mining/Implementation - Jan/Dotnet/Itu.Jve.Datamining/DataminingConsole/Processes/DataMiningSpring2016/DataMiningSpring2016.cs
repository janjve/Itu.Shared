﻿using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using DataminingConsole.Processes.DataMiningSpring2016.Common;
using DataminingConsole.Processes.DataMiningSpring2016.Entities;
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

        public void Run()
        {
            var data = DataSelection();
            var dataset = PreProcessing(data);
            PatternDiscovery(dataset);
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
            Debug.WriteLine("");
            Debug.WriteLine("================================");
            Debug.WriteLine("== STARTING PATTERN DISCOVERY ==");
            Debug.WriteLine("================================");
            Debug.WriteLine("");

            dataSet.OrderBy(x => x.Age).ToList().ForEach(x => Debug.WriteLine(x));

            Debug.WriteLine("");
            Debug.WriteLine("================================");
            Debug.WriteLine("=== ENDING PATTERN DISCOVERY ===");
            Debug.WriteLine("================================");
            Debug.WriteLine("");
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