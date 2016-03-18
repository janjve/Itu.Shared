using System;
using System.Linq;

namespace DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Apriori.Attribute
{
    public class ItemSet : IComparable
    {
        // An array of all the attributes the ItemSet contains
        public Entities.Attributes.Abstract.Attribute[] Set { get; set; }
        // The support for the ItemSet
        public int Support { get; set; }

        /**
        * hashCode functioned used internally in Hashtable
        */
        public override int GetHashCode()
        {
            int code = 0;
            for (int i = 0; i < Set.Length; i++)
            {
                code += unchecked(Set[i].ToString().GetHashCode());
            }
            return code;
        }

        /**
        * Used to determine whether two ItemSet objects are equal
        */
        public override bool Equals(object obj)
        {
            if (obj == null) return false;
            var other = obj as ItemSet;

            if (other != null)
            {
                if (other.Set.Length != this.Set.Length)
                {
                    return false;
                }
                return !Set.Where((t, i) => !t.ToString().Equals(other.Set[i].ToString())).Any();
            }
            else
                throw new ArgumentException("Object is not an ItemSet");
        }

        public int CompareTo(object obj)
        {
            throw new NotImplementedException();
        }

        public override string ToString()
        {
            var all = Set.Aggregate("", (current, item) => current + (item.ToString() + ","));

            return all.Remove(all.Length - 1);
        }
    }
}
