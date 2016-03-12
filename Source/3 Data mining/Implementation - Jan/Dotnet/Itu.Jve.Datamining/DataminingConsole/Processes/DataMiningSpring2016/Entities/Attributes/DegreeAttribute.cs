using System;

namespace DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes
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

        public override string ToString()
        {
            return Value.ToString();
        }
    }

    public enum DegreeType
    {
        Unknown,
        PolIT,
        GAMES_A,
        GAMES_T,
        SDT_SE,
        SDT_DT,
        SWU,
        Guest_Student
    }
}
