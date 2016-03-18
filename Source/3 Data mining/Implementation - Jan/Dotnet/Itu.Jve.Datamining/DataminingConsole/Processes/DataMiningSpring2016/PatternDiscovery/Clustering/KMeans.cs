using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using DataminingConsole.Processes.DataMiningSpring2016.Entities;

namespace DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Clustering
{
    /// <summary>
    /// KMeans clusterings algorithm container.
    /// </summary>
    public class KMeans
    {
        /// <summary>
        /// Method to call to start the KMeansClustering process.
        /// </summary>
        /// <param name="k">number of clusters</param>
        /// <param name="data">dataset to cluster</param>
        /// <returns></returns>
        public static List<KMeanCluster> KMeansPartition(int k, List<DataTuple> data)
        {
            var clusters = GenerateInitialPartioningCluster(k, data);
            ReassignedResponse response;
            do
            {
                response = ReassignDataToCluster(data, clusters);
                clusters = response.Clusters;
            } while (response.Reassigned);

            return clusters;
        }

        /// <summary>
        /// Reassign tuples to nearest cluster
        /// </summary>
        /// <param name="data">dataset</param>
        /// <param name="clusters">currentclusters</param>
        /// <returns>new clusters + if it changed from currentclusters</returns>
        private static ReassignedResponse ReassignDataToCluster(IEnumerable<DataTuple> data, IEnumerable<KMeanCluster> clusters)
        {
            var newOldClusterIndex = new Dictionary<KMeanCluster, KMeanCluster>();

            var reassigned = false;

            // Create cluster index to map between current and new.
            foreach (var cluster in clusters)
            {
                var newCluster = new KMeanCluster { ClusterMean = cluster.CalculateClusterMean() };
                newOldClusterIndex.Add(newCluster, cluster);
            }

            // initialize new cluster
            var newClusters = new List<KMeanCluster>(newOldClusterIndex.Keys);

            // Assign tuples to new clusters.
            foreach (var record in data)
            {
                KMeanCluster minManhattenDistanceCluster = null;
                var minDistance = float.MaxValue;

                // Find closest cluster average.
                foreach (var cluster in newOldClusterIndex.Keys)
                {
                    var distance = Helpers.ManhattenDistance(record, cluster.ClusterMean);
                    
                    if (minDistance > distance)
                    {
                        minManhattenDistanceCluster = cluster;
                        minDistance = distance;
                    }
                }

                Debug.Assert(minManhattenDistanceCluster != null, "minManhattenDistanceCluster != null");

                minManhattenDistanceCluster.ClusterMembers.Add(record);

                // If changed cluster set reassigned to true.
                if (!reassigned && !newOldClusterIndex[minManhattenDistanceCluster].ClusterMembers.Contains(record))
                {
                    reassigned = true;
                }
            }

            return new ReassignedResponse { Clusters = newClusters, Reassigned = reassigned };
        }

        /// <summary>
        /// Use first k elements in data to create initial clusters
        /// </summary>
        /// <param name="k">number of clusters</param>
        /// <param name="data">dataset</param>
        /// <returns></returns>
        private static List<KMeanCluster> GenerateInitialPartioningCluster(int k, List<DataTuple> data)
        {
            if (data == null) throw new ArgumentNullException(nameof(data));

            return data.Take(k).Select(initialData => new KMeanCluster
            {
                ClusterMembers = new List<DataTuple> { initialData }
            }).ToList();
        }
    }
}
