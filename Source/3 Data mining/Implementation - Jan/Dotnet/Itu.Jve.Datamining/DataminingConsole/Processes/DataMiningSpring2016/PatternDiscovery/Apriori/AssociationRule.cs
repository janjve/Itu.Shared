using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Apriori
{
    //Association rules, to be output at the end.
    public class AssociationRule
    {
        public ItemSet Premise { get; set; }
        public ItemSet Conclusion { get; set; }
        public double Confidence { get; set; }
        public int Support { get; set; }

        public override string ToString()
        {
            return base.ToString();
        }
    }
}
