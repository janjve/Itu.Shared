using System.Collections.Generic;
using System.Linq;
using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes;
using DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Clustering;
using DataminingConsole.Processes.DataMiningSpring2016.Preprocessing;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace DataMiningSping2016Tests.PatternDiscovery
{
    [TestClass]
    public class ClusteringTests
    {
        [TestClass]
        public class KMeansTests
        {
            private List<DataTuple> _testData;

            [TestInitialize]
            public void Setup()
            {
                _testData = new List<DataTuple>();
            }

            [TestMethod]
            public void TwoClustersNumeric()
            {
                // Arrange
                _testData.Add(CreateDataTuple(age: 4));
                _testData.Add(CreateDataTuple(age: 3));
                _testData.Add(CreateDataTuple(age: 5));
                _testData.Add(CreateDataTuple(age: 54));
                _testData.Add(CreateDataTuple(age: 60));
                _testData.MinMaxNormalizeDataTuple();

                // Act
                var result = KMeans.KMeansPartition(2, _testData);

                var lowCluster = result.First(x => x.ClusterMembers.Any(y => y.Age.Value < 20));
                var highCluster = result.First(x => x.ClusterMembers.Any(y => y.Age.Value > 40));

                // Assert
                Assert.AreEqual(result.Count, 2, "Should only create two clusters");
                Assert.AreEqual(lowCluster.ClusterMembers.Count, 3, "Low cluster should contain 3 elements");
                Assert.AreEqual(highCluster.ClusterMembers.Count, 2, "Low cluster should contain 2 elements");
            }

            [TestMethod]
            public void TwoClustersNomical()
            {
                // Arrange
                _testData.Add(CreateDataTuple(degreeType: DegreeType.GAMES_T));
                _testData.Add(CreateDataTuple(degreeType: DegreeType.SDT_DT));
                _testData.Add(CreateDataTuple(degreeType: DegreeType.SDT_DT));
                _testData.Add(CreateDataTuple(degreeType: DegreeType.GAMES_T));
                _testData.Add(CreateDataTuple(degreeType: DegreeType.SDT_DT));
                _testData.MinMaxNormalizeDataTuple();

                // Act
                var result = KMeans.KMeansPartition(2, _testData);

                var cluster1 = result.First(x => x.ClusterMembers.Any(y => y.Degree.Value == DegreeType.GAMES_T));
                var cluster2 = result.First(x => x.ClusterMembers.Any(y => y.Degree.Value == DegreeType.SDT_DT));

                // Assert
                Assert.AreEqual(result.Count, 2, "Should only create two clusters");
                Assert.AreEqual(cluster1.ClusterMembers.Count, 2, "cluster1 should contain 2 elements");
                Assert.AreEqual(cluster2.ClusterMembers.Count, 3, "cluster2 should contain 3 elements");
            }

            [TestMethod]
            public void TwoClustersMultiple()
            {
                // Arrange
                _testData.Add(CreateDataTuple(age: 21, degreeType: DegreeType.SDT_DT, favoriteGameType: FavoriteGameType.Baldurs_Gate));        // 1
                _testData.Add(CreateDataTuple(age: 40, degreeType: DegreeType.GAMES_A, favoriteGameType: FavoriteGameType.Call_Of_Duty_2));     // 2
                _testData.Add(CreateDataTuple(age: 54, degreeType: DegreeType.GAMES_A, favoriteGameType: FavoriteGameType.Call_Of_Duty_2));     // 2
                _testData.Add(CreateDataTuple(age: 20, degreeType: DegreeType.SDT_DT, favoriteGameType: FavoriteGameType.Baldurs_Gate));        // 1
                _testData.Add(CreateDataTuple(age: 56, degreeType: DegreeType.SDT_DT, favoriteGameType: FavoriteGameType.Call_Of_Duty_2));      // 2 - special case
                _testData.MinMaxNormalizeDataTuple();

                // Act
                var result = KMeans.KMeansPartition(2, _testData);

                var cluster1 = result.First(x => x.ClusterMembers.Any(y => y.FavoriteGame.Value == FavoriteGameType.Baldurs_Gate));
                var cluster2 = result.First(x => x.ClusterMembers.Any(y => y.FavoriteGame.Value == FavoriteGameType.Call_Of_Duty_2));

                // Assert
                Assert.AreEqual(result.Count, 2, "Should only create two clusters");
                Assert.AreEqual(cluster1.ClusterMembers.Count, 2, "cluster1 should contain 2 elements");
                Assert.AreEqual(cluster2.ClusterMembers.Count, 3, "cluster2 should contain 3 elements");
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
