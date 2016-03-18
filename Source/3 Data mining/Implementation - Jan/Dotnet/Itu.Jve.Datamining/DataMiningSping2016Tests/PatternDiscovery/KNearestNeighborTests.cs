using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes;
using DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Clustering;
using DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.KNearestNeighbors;
using DataminingConsole.Processes.DataMiningSpring2016.Preprocessing;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Attribute = DataminingConsole.Processes.DataMiningSpring2016.Entities.Attribute;

namespace DataMiningSping2016Tests.PatternDiscovery
{
    [TestClass]
    public class KNearestNeighborTests
    {
        private List<DataTuple> _testData;
        private KNearestNeighbor _sut;

        [TestInitialize]
        public void Setup()
        {
            _testData = new List<DataTuple>();
            _sut = new KNearestNeighbor();
        }

        [TestMethod]
        public void KNearestNeighborSimple()
        {
            // Arrange
            var trainingData = new Dictionary<DataTuple, AgeGroupTest>
            {
                {  CreateDataTuple(age: 3), AgeGroupTest.Low },
                {  CreateDataTuple(age: 4), AgeGroupTest.Low },
                {  CreateDataTuple(age: 5), AgeGroupTest.Low },
                {  CreateDataTuple(age: 6), AgeGroupTest.Low },
                {  CreateDataTuple(age: 7), AgeGroupTest.Low },
                {  CreateDataTuple(age: 8), AgeGroupTest.Low },
                {  CreateDataTuple(age: 32), AgeGroupTest.Medium },
                {  CreateDataTuple(age: 33), AgeGroupTest.Medium },
                {  CreateDataTuple(age: 63), AgeGroupTest.High },
                {  CreateDataTuple(age: 64), AgeGroupTest.High },
                {  CreateDataTuple(age: 65), AgeGroupTest.High },
                {  CreateDataTuple(age: 66), AgeGroupTest.High },
                {  CreateDataTuple(age: 67), AgeGroupTest.High },
                {  CreateDataTuple(age: 68), AgeGroupTest.High },
                {  CreateDataTuple(age: 69), AgeGroupTest.High }
            };
            
            var unassigned = CreateDataTuple(age: 60);
            
            // Normalize all
            var dataSet = trainingData.Keys.ToList();
            dataSet.Add(unassigned);
            dataSet.MinMaxNormalizeDataTuple();

            // Act
            var result = _sut.CalculateClassification<AgeGroupTest>(5, unassigned, trainingData);

            // Assert
            Assert.AreEqual(result, AgeGroupTest.High, "Should be in high age group");
        }

        #region helpers
        private enum AgeGroupTest
        {
            Low,
            Medium,
            High
        }

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
