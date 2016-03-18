using System;
using System.Collections.Generic;
using DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes.Abstract;
using Attribute = DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes.Abstract.Attribute;

namespace DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes
{
    [Obsolete("Unused")]
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

        public override string ToString()
        {
            return Value?.Count.ToString() ?? "NULL";
        }
    }

    public enum PlayedGameType
    {
        Candy_Crush,
        Wordfeud,
        Minecraft,
        FarmVille,
        Fifa_2016,
        Star_Wars_Battlefront,
        Life_is_Strange,
        Battlefield_4,
        Journey,
        Gone_Home,
        Stanley_Parable,
        Call_of_Duty_Black_Ops_III,
        Rocket_League,
        Bloodborne,
        Rise_of_the_Tomb_Raider,
        The_Witness,
        Her_Story,
        Fallout_4,
        Dragon_Age_Inquisition,
        Counter_strike_GO,
        Angry_birds,
        The_Last_of_Us,
        The_Magic_Circle,
        I_have_not_played_any_of_these_games,
    }
}
