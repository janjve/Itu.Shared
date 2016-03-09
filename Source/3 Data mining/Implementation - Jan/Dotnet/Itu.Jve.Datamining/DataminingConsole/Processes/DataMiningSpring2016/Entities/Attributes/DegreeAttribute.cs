using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataminingConsole.Processes.DataMiningSpring2016.Entities
{
    public class DegreeAttribute : Attribute
    {
        public DegreeAttribute() : base(AttributeType.Degree) { }

        public DegreeType Value { get; set; }

        public string OtherFreetext { get; set; }
        public override int CompareTo(object obj)
        {
            if (obj == null) return 1;
            var otherAgeAttribute = obj as DegreeAttribute;

            if (otherAgeAttribute != null)
                return Value.CompareTo(otherAgeAttribute.Value);
            else
                throw new ArgumentException("Object is not an DegreeAttribute");
        }
    }

    public enum DegreeType
    {
        Other,
        GAMES_A,
        GAMES_T,
        SDT_SE,
        SDT_DT,
        SWU,
        Guest_Student
    }
}
