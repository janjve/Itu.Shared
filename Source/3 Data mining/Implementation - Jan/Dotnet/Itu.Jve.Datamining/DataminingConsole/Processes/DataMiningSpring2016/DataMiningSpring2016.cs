﻿using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using DataminingConsole.Processes.DataMiningSpring2016.Common;
using DataminingConsole.Processes.DataMiningSpring2016.Utility;
using DataminingTools.FileIO;
using DataminingTools.Preprocessing;

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
                .ToDictionary((x) => DataMappers.AttributeTypeMapper(x.key), x => x.value);

            // Attributelist temp.
            attributeList = _csvAttributeNames;
            Log(dataset, "Attributes selected");

            dataset.MapColumn(attributeList, Constant.CsvAttributeName.Age, _dataCleaningHandler.AgeCleaner);

            var transformedDataset = dataset.Select(x => _dataTransformationHandler.TransformTuple(x, attributeIndex));

            Log(dataset, "Age cleaned");
        }

        #region Private members
        private static void Log(List<List<string>> results, string header)
        {
            var output = DebugStringBuilder.HeaderString(header)
                + results.CsvArrayToString()
                + $"{Environment.NewLine}[INFO ]\tColumns: {results[0].Count}, Rows: {results.Count - 1}";
            Debug.WriteLine(output);

            LogWriter.WriteLogToFile(@"log.txt", output, false);
        }
        #endregion
    }
}
