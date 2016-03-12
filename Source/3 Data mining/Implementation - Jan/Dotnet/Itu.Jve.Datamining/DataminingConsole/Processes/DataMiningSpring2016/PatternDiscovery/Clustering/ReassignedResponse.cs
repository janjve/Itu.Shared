using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Clustering
{
    internal class ReassignedResponse
    {
        public bool Reassigned { get; set; }
        public List<KMeanCluster> Clusters { get; set; }

        public ReassignedResponse()
        {
            Clusters = new List<KMeanCluster>();
        }
    }
}
