using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes;

namespace DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Clustering
{
    public class KMeanCluster
    {
        // DataTuples in the cluster
        public List<DataTuple> ClusterMembers { get; set; }

        // Cluster average
        public DataTuple ClusterMean { get; set; }

        public KMeanCluster()
        {
            ClusterMembers = new List<DataTuple>();
        }

        /// <summary>
        /// Recalculate the clustermean
        /// </summary>
        /// <returns></returns>
        public DataTuple CalculateClusterMean()
        {
            // This is a lazy implementation, but is easier to read.
            var ageSum = ClusterMembers.Sum(x => x.Age.NormalizedValue);
            var degree = ClusterMembers.GroupBy(x => x.Degree.Value).OrderByDescending(group => group.Count()).First().Key;
            var favoriteGame = ClusterMembers.GroupBy(x => x.FavoriteGame.Value).OrderByDescending(group => group.Count()).First().Key;
            var gameFrequency = ClusterMembers.GroupBy(x => x.GameFrequency.Value).OrderByDescending(group => group.Count()).First().Key;

            ClusterMean = new DataTuple
            {
                Age = new AgeAttribute { NormalizedValue = ageSum / ClusterMembers.Count },
                Degree = new DegreeAttribute { Value = degree },
                FavoriteGame = new FavoriteGameAttribute { Value = favoriteGame },
                GameFrequency = new GameFrequencyAttribute { Value = gameFrequency },
            };
            return ClusterMean;
        }

        /// <summary>
        /// String override
        /// </summary>
        /// <returns></returns>
        public override string ToString()
        {
            var stringToPrint = "-----------------------------------CLUSTER START------------------------------------------" + Environment.NewLine;
            ClusterMembers.ForEach(x => { stringToPrint += x.ToString() + Environment.NewLine; });
            stringToPrint += "---------------------------------------CLUSTER END-------------------------------------------" + Environment.NewLine;
            return stringToPrint;
        }
    }
}
