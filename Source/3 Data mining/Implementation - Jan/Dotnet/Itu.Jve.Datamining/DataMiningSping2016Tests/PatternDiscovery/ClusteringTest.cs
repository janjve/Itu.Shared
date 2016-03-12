using System.Collections.Generic;
using System.Linq;
using DataminingConsole.Processes.DataMiningSpring2016.Common;
using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes;
using DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Clustering;
using DataminingConsole.Processes.DataMiningSpring2016.Preprocessing;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace DataMiningSping2016Tests.PatternDiscovery
{
    [TestClass]
    public class ClusteringTest
    {
        [TestClass]
        public class KMeansTest
        {
            private List<DataTuple> testData;
            [TestInitialize]
            public void Setup()
            {
                testData = new List<DataTuple>();
            }

            [TestMethod]
            public void TwoClusters()
            {
                // Arrange
                testData.Add(CreateDataTuple(10));
                testData.Add(CreateDataTuple(9));
                testData.Add(CreateDataTuple(12));
                testData.Add(CreateDataTuple(54));
                testData.Add(CreateDataTuple(60));

                // Act
                var result = KMeans.KMeansPartition(2, testData);

                var lowCluster = result.First(x => x.ClusterMembers.Any(y => y.Age.Value < 20));
                var highCluster = result.First(x => x.ClusterMembers.Any(y => y.Age.Value > 40));

                // Assert
                Assert.AreEqual(result.Count, 2, "Should only create two clusters");
                Assert.AreEqual(lowCluster.ClusterMembers.Count, 3, "Low cluster should contain 3 elements");
                Assert.AreEqual(highCluster.ClusterMembers.Count, 2, "Low cluster should contain 2 elements");
            }

            #region data

            private static DataTuple CreateDataTuple(int age = 30,
                DegreeType degreeType = DegreeType.GAMES_A,
                FavoriteGameType favoriteGameType = FavoriteGameType.Advanced_Wars_Dual_Strike,
                GameFrequencyType gameFrequencyType = GameFrequencyType.LessThan10HoursAWeek,
                PlayedGameType[] playedGameTypes = null)
            {
                if (playedGameTypes == null) { playedGameTypes = new[] { PlayedGameType.Angry_birds }; }

                return new DataTuple
                {
                    Age = new AgeAttribute { Value = age },
                    Degree = new DegreeAttribute { Value = degreeType },
                    FavoriteGame = new FavoriteGameAttribute { Value = favoriteGameType },
                    GameFrequency = new GameFrequencyAttribute { Value = gameFrequencyType },
                    PlayedGames = new PlayedGamesAttribute { Value = new List<PlayedGameType>(playedGameTypes) }
                };
            }
            #endregion
        }

    }
}
