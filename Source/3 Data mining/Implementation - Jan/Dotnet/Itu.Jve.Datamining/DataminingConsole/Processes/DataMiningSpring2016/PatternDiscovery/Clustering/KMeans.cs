﻿using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using DataminingConsole.Processes.DataMiningSpring2016.Entities;

namespace DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Clustering
{
    public class KMeans
    {
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

        private static ReassignedResponse ReassignDataToCluster(IEnumerable<DataTuple> data, IEnumerable<KMeanCluster> clusters)
        {
            var newOldClusterIndex = new Dictionary<KMeanCluster, KMeanCluster>();

            var reassigned = false;

            foreach (var cluster in clusters)
            {
                var newCluster = new KMeanCluster { ClusterMean = cluster.CalculateClusterMean() };
                newOldClusterIndex.Add(newCluster, cluster);
            }

            var newClusters = new List<KMeanCluster>(newOldClusterIndex.Keys);

            foreach (var record in data)
            {
                KMeanCluster minManhattenDistanceCluster = null;
                var minDistance = float.MaxValue;

                foreach (var cluster in newOldClusterIndex.Keys)
                {
                    var distance = ManhattenDistance(record, cluster.ClusterMean);

                    if (minDistance > distance)
                    {
                        minManhattenDistanceCluster = cluster;
                        minDistance = distance;
                    }
                }

                Debug.Assert(minManhattenDistanceCluster != null, "minManhattenDistanceCluster != null");

                minManhattenDistanceCluster.ClusterMembers.Add(record);

                if (!reassigned && !newOldClusterIndex[minManhattenDistanceCluster].ClusterMembers.Contains(record))
                {
                    reassigned = true;
                }
            }

            return new ReassignedResponse { Clusters = newClusters, Reassigned = reassigned };
        }

        private static List<KMeanCluster> GenerateInitialPartioningCluster(int k, List<DataTuple> data)
        {
            if (data == null) throw new ArgumentNullException(nameof(data));

            return data.Take(k).Select(initialData => new KMeanCluster
            {
                ClusterMembers = new List<DataTuple> { initialData }
            }).ToList();
        }

        // Nominal distance is 0 if equal, 1 otherwise.
        private static float ManhattenDistance(DataTuple tuple1, DataTuple tuple2)
        {
            var degreeDistance = tuple1.Degree.Value == tuple2.Degree.Value ? 0.0f : 1.0f;
            var favoriteGameDistance = tuple1.FavoriteGame.Value == tuple2.FavoriteGame.Value ? 0.0f : 1.0f;
            var gameFrequencyDistance = tuple1.GameFrequency.Value == tuple2.GameFrequency.Value ? 0.0f : 1.0f;

            var distance = Math.Abs(tuple1.Age.NormalizedValue - tuple2.Age.NormalizedValue)
                + degreeDistance
                + favoriteGameDistance
                + gameFrequencyDistance
                ;
            return distance;
        }
    }
}