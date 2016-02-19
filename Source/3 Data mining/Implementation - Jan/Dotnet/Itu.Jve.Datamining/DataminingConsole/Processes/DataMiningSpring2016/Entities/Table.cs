using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataminingConsole.Processes.DataMiningSpring2016.Entities
{
    public class Table
    {
        public List<AttributeType> Attributes { get; set; }
        public List<DataTuple> Dataset { get; set; }
    }
}
