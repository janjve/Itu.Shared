using System.Collections.Generic;
using DataminingConsole.Processes.DataMiningSpring2016.Common;
using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using DataminingConsole.Processes.DataMiningSpring2016.Entities.Age;

namespace DataminingConsole.Processes.DataMiningSpring2016.Preprocessing
{
    // Values that can't be transformed are considered "missing"
    public class DataTransformationHandler
    {

        public DataTuple TransformTuple(List<string> tuple, Dictionary<AttributeType, int> attributeIndex)
        {
            return new DataTuple
            {
                Age = TransformAge(tuple[attributeIndex[AttributeType.Age]]),
                //Degree = TransformAge(tuple[attributeIndex[AttributeType.Age]]),
                //FavoriteGame = TransformAge(tuple[attributeIndex[AttributeType.Age]]),
                //GameFrequency = TransformAge(tuple[attributeIndex[AttributeType.Age]]),
                //PlayedGames = TransformAge(tuple[attributeIndex[AttributeType.Age]])
            };
        } 

        public AgeAttribute TransformAge(string age)
        {
            int transformedAge;

            if (int.TryParse(age, out transformedAge))
                return new AgeAttribute {Value = transformedAge };
            return null;
        }
    }
}
