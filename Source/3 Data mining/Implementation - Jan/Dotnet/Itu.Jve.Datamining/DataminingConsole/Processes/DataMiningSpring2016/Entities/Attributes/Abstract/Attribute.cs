using System;
using DataminingConsole.Processes.DataMiningSpring2016.Common;

namespace DataminingConsole.Processes.DataMiningSpring2016.Entities
{
    public abstract class Attribute : IComparable
    {
        protected Attribute(AttributeType attributeType)
        {
            AttributeType = attributeType;
        }
        
        public bool MissingValue { get; set; }
        
        public AttributeType AttributeType { get; private set; }

        public abstract int CompareTo(object obj);
    }
}
