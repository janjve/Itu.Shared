using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Clustering
{
    public class KMeans
    {
        public static List<KMeanCluster> KMeansPartition(int k, List<DataTuple> data)
        {
            var clusters = GenerateInitialPartioningCluster(k, data);

            return null;
        }

        private static ReassignedResponse ReassignDataToCluster(List<DataTuple> data, List<KMeanCluster> clusters)
        {
            return null;
        }

        private static float ManhattenDistance(DataTuple tuple1, DataTuple tuple2)
        {
            return float.MaxValue;
        }

        private static List<KMeanCluster> GenerateInitialPartioningCluster(int k, List<DataTuple> data)
        {
            return null;
        }
    }
}
