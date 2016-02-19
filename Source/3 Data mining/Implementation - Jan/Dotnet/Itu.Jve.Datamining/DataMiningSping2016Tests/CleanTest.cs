using System;
using System.Collections.Generic;
using DataminingConsole.Processes.DataMiningSpring2016.Common;
using DataminingConsole.Processes.DataMiningSpring2016.Preprocessing;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace DataMiningSping2016Tests
{
    [TestClass]
    public class CleanTest
    {
        [TestClass]
        public class AgeCleanerTests
        {
            #region setup
            private List<List<string>> _dataset;
            private DataCleaningHandler _sut;

            [TestInitialize]
            public void Setup()
            {
                _dataset = new List<List<string>>
                {
                    new List<string> {"a", "jan"},
                    new List<string> {"32", "søren"},
                    new List<string> {"", "jette"},
                    new List<string> {null, "kirsten"},
                };
                _sut = new DataCleaningHandler();
            }
            #endregion

            [TestMethod]
            public void Valid()
            {
                var expected = _dataset[1][0];
                var actual = _sut.AgeCleaner(_dataset[1][0]);

                Assert.AreEqual(expected, actual);
            }

            [TestMethod]
            public void Invalid()
            {
                var expected = Constant.InternalString.ParsingError;
                var actual = _sut.AgeCleaner(_dataset[0][0]);

                Assert.AreEqual(expected, actual);
            }
            [TestMethod]
            public void EmptyAndNull()
            {
                var expected = Constant.InternalString.MissingDataError;
                var actual1 = _sut.AgeCleaner(_dataset[2][0]);
                var actual2 = _sut.AgeCleaner(_dataset[3][0]);

                Assert.AreEqual(expected, actual1);
                Assert.AreEqual(expected, actual2);
            }
        }

    }
}
