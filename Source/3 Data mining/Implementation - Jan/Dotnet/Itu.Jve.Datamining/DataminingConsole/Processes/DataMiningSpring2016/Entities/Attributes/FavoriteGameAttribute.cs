using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataminingConsole.Processes.DataMiningSpring2016.Entities
{
    public class FavoriteGameAttribute : Attribute
    {
        public FavoriteGameAttribute() : base(AttributeType.FavoriteGame) { }

        public FavoriteGameType Value { get; set; }

        public override int CompareTo(object obj)
        {
            if (obj == null) return 1;
            var otherFavoriteGameAttribute = obj as FavoriteGameAttribute;

            if (otherFavoriteGameAttribute != null)
                return Value.CompareTo(otherFavoriteGameAttribute.Value);
            else
                throw new ArgumentException("Object is not an FavoriteGameAttribute");
        }
    }

    public enum FavoriteGameType
    {
        World_Of_Tank,
        World_Of_Warcraft,
        Yu_Gi_Oh,
        Hearthstone,
        Snake,
        Hitman,
        Streets_Of_Rage,
        Tetris,
        Witcher_3,
        Skyrim,
        Gone_Home,
        Counter_Strike,
        FEZ,
        Diablo_2,
        Path_Of_Exile,
        Dota_2,
        Spec_Ops_The_Line,
        Fifa_16,
        Vampire_The_Masquerade_Bloodlines,
        Half_Life,
        Trine,
        Galactic_Arms_Race,
        Fifa,
        Championship_Manager,
        Deus_Ex,
        Transport_Tycoon,
        Baldurs_Gate,
        Journey,
        Ico,
        Heroes_Of_Newerth,
        Call_Of_Duty_2,
        Heroes_Of_The_Storm,
        The_Binding_Of_Isaac_Rebirth,
        Starcraft,
        Risk,
        Counter_Strike_Go,
        Altos_Adventure,
        Zelda,
        League_Of_Legends,
        Batman_Arkham_Knight,
        Portal,
        Advanced_Wars_Dual_Strike,
        Prototype,
        Unknown,
        Last_Of_Us
    }
}
