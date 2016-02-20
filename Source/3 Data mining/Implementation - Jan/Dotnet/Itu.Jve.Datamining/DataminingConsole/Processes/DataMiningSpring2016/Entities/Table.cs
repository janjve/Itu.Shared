using System.Collections.Generic;

namespace DataminingConsole.Processes.DataMiningSpring2016.Entities
{
    public class Table
    {
        public List<AttributeType> Attributes { get; set; }
        public List<DataTuple> Dataset { get; set; }
    }
}
