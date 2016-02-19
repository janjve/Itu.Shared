using System;
using DataminingConsole.Processes.DataMiningSpring2016.Common;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace DataminingToolsTests
{
    [TestClass]
    public class CsvFileReaderTests
    {
        [TestMethod]
        public void CouldReadFile()
        {
            var result = CsvFileReader.ReadDataFile(@"Testdata\SimpleCsvFile.csv");
            Assert.IsTrue(result != null, "Should be able to open file");
        }
    }
}
