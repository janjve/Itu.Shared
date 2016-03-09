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
        A,
    }
}
