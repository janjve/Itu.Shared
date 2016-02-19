using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DataminingConsole.Processes.DataMiningSpring2016.Entities;

namespace DataminingConsole.Processes.DataMiningSpring2016.Utility
{
    // Values that can't be transformed are considered "missing"
    public class DataTransformationHandler
    {

        public DataTuple TransformTuple(List<string> tuple, Dictionary<AttributeType, int> attributeIndex)
        {
            return new DataTuple
            {
                Age = TransformAge(tuple[attributeIndex[AttributeType.Age]])
            };
        } 

        public AgeAttribute TransformAge(string age)
        {
            int transformedAge;
            // Simple transform - Could try harder to transform.
            if (int.TryParse(age, out transformedAge))
                return new AgeAttribute {Value = transformedAge };
            return null;
        }
    }
}
