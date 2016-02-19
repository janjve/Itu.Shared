using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using DataminingTools.FileIO;

namespace DataminingToolsTests
{
    [TestClass]
    public class CsvFileReaderTests
    {
        [TestMethod]
        public void CoulReadFile()
        {
            var result = CsvFileReader.ReadDataFile(@"Testdata\SimpleCsvFile.csv");
            Assert.IsTrue(result != null, "Should be able to open file");
        }
    }
}
