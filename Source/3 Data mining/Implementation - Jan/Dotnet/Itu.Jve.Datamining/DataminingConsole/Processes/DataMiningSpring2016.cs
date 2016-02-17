using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DataminingTools.FileIO;
using DataminingTools.Preprocessing;

namespace DataminingConsole.Processes
{
    public class DataMiningSpring2016 : IDataminingProcesses
    {
        #region Configuration
        private readonly string _csvPath;
        public DataMiningSpring2016()
        {
            _csvPath = @"Data\DataMining 2016 (Responses).csv";
        }
        #endregion

        public void Start()
        {
            var result = CsvFileReader.ReadDataFile(_csvPath);              // Load CSV file into memory
            var selectedAttributes = result.SelectAttributes();             // Reduce attributes


        }
    }
}
