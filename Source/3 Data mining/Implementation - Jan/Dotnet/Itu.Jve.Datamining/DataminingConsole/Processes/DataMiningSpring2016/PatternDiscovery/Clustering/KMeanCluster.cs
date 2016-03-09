using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Clustering
{
    public class KMeanCluster
    {
        public List<DataTuple> ClusterMembers { get; set; }

        public KMeanCluster()
        {
            this.ClusterMembers = new List<DataTuple>();
        }

        public override string ToString()
        {
            string stringToPrint = "-----------------------------------CLUSTER START------------------------------------------" + Environment.NewLine;

            stringToPrint += ClusterMembers.Select(x => x + Environment.NewLine);

            stringToPrint += "-----------------------------------CLUSTER END------------------------------------------" + Environment.NewLine;
            return stringToPrint;
        }
    }
}
