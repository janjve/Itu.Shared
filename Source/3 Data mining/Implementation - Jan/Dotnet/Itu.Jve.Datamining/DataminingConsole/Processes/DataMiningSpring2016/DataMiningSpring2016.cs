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

        public DataMiningSpring2016()
        {
            _csvAttributeNames = new List<string>()
            {
                Constant.CsvAttributeName.Age,
                Constant.CsvAttributeName.Row,
                Constant.CsvAttributeName.Seat,
                Constant.CsvAttributeName.Degree,
                Constant.CsvAttributeName.GameFrequency,
                Constant.CsvAttributeName.FavoriteGame,
                Constant.CsvAttributeName.PickNumber,
            }; ;
            _csvPath = @"Data\DataMining 2016 (Responses).csv";
            _dataCleaningHandler = new DataCleaningHandler();
            _dataTransformationHandler = new DataTransformationHandler();
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

            // Create attributeIndex
            var attributeIndex = _csvAttributeNames
                .Select((x, i) => new { key = x, value = i })
                .ToDictionary(x => DataMappers.AttributeTypeMapper(x.key), x => x.value);
            Logger.Log(dataset, "Attributes selected");

            dataset.MapColumn(attributeIndex, AttributeType.Age, _dataCleaningHandler.AgeCleaner);

            var transformedDataset = dataset.Select(x => _dataTransformationHandler.TransformTuple(x, attributeIndex)).ToList();

            var ageSet = transformedDataset.Select(x => x.Age).ToList();
            var mean = ageSet.Mean();
            var median = ageSet.Median();
            var mode = ageSet.Mode();
            var midrange = ageSet.Midrange();

            Debug.WriteLine($"Age mean : {mean}");
            Debug.WriteLine($"Age median : {median}");
            Debug.WriteLine($"Age mode : {mode}");
            Debug.WriteLine($"Age midrange : {midrange}");

            transformedDataset.OrderBy(x => x.Age).ToList().ForEach(x => Debug.WriteLine(x));
        }

        /*
        Flow:
        - Reduce attribute list.
        - Remove header list
        - Create attributeIndex, dictionary
        - 
        */
    }
}
