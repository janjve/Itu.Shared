using System;

namespace DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes
{
    public class GameFrequencyAttribute : Attribute
    {
        public GameFrequencyAttribute() : base(AttributeType.GameFrequency) { }

        public GameFrequencyType Value { get; set; }

        public override int CompareTo(object obj)
        {
            if (obj == null) return 1;
            var otherGameFrequencyAttribute = obj as GameFrequencyAttribute;

            if (otherGameFrequencyAttribute != null)
                return Value.CompareTo(otherGameFrequencyAttribute.Value);
            else
                throw new ArgumentException("Object is not an GameFrequencyAttribute");
        }
        public override string ToString()
        {
            return Value.ToString();
        }
    }

    public enum GameFrequencyType
    {
        Never,
        LessThan5HoursAWeek,
        LessThan10HoursAWeek,
        LessThan15HoursAWeek,
        LessThan20HoursAWeek,
        MoreThan20HoursAWeek
    }
}
