﻿using System;

namespace DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes.Abstract
{
    public abstract class Attribute : IComparable
    {
        protected Attribute(AttributeType attributeType)
        {
            AttributeType = attributeType;
        }
        
        public bool MissingValue { get; set; }
        
        public AttributeType AttributeType { get; private set; }
        public float NormalizedValue { get; set; }

        public abstract int CompareTo(object obj);
    }
}
