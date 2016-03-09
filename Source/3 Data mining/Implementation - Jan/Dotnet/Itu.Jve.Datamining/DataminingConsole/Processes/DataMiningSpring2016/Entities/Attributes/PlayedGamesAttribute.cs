using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataminingConsole.Processes.DataMiningSpring2016.Entities
{
    public class PlayedGamesAttribute : Attribute
    {
        public PlayedGamesAttribute() : base(AttributeType.PlayedGames) { }

        public List<PlayedGameType> Value { get; set; }

        public override int CompareTo(object obj)
        {
            if (obj == null) return 1;
            var otherPlayedGameAttribute = obj as PlayedGamesAttribute;

            if (otherPlayedGameAttribute != null)
                return 0; // TODO
                //return Value.all(otherPlayedGameAttribute.Value);
            else
                throw new ArgumentException("Object is not an PlayedGameAttribute");
        }
    }

    public enum PlayedGameType
    {
        A, // .....
    }
}
