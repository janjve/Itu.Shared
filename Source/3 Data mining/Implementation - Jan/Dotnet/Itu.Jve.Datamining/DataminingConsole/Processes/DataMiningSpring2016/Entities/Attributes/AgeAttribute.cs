using System;
using DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes.Abstract;
using Attribute = DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes.Abstract.Attribute;

namespace DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes
{
    public class AgeAttribute : Attribute
    {
        public AgeAttribute() : base(AttributeType.Age) { }

        public int Value { get; set; }

        public override string ToString()
        {
            return Value.ToString();
        }

        public override int CompareTo(object obj)
        {
            if (obj == null) return 1;
            var otherAgeAttribute = obj as AgeAttribute;

            if (otherAgeAttribute != null)
                return Value.CompareTo(otherAgeAttribute.Value);
            else
                throw new ArgumentException("Object is not an AgeAttribute");
        }
    }
}
