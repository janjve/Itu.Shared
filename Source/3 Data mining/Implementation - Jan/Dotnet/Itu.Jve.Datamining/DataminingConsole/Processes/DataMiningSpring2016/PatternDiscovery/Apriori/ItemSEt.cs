using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.Apriori
{
    public class ItemSet : IComparable
    {
        /***
            * The PRIMES array is internally in the ItemSet-class' hashCode method
        */
        private static int[] PRIMES = { 2, 3, 5, 7, 11, 13, 17, 23, 27, 31, 37 };
        public int[] Set { get; set; }

        /**
        * hashCode functioned used internally in Hashtable
        */
        public override int GetHashCode()
        {
            int code = 0;
            for (int i = 0; i < Set.Length; i++)
            {
                code += Set[i] * PRIMES[i];
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
                for (int i = 0; i < Set.Length; i++)
                {
                    if (Set[i] != other.Set[i])
                    {
                        return false;
                    }
                }
                return true;
            }
            else
                throw new ArgumentException("Object is not an AgeAttribute");
        }

        public int CompareTo(object obj)
        {
            throw new NotImplementedException();
        }
    }
}
