using DataminingConsole.Processes;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;
using DataminingConsole.Processes.DataMiningSpring2016;

namespace DataminingConsole
{
    class Program
    {
        static void Main(string[] args)
        {
            var dataminingProcess = new DataMiningSpring2016();
            Console.BufferHeight = short.MaxValue - 1;
            Console.WriteLine(string.Format("Starting mining process: {0} ...", dataminingProcess.GetType()));
            dataminingProcess.Start();
            Console.WriteLine(string.Format("Completed mining process: {0}.", dataminingProcess.GetType()));
            Console.WriteLine("Press enter to terminate..");
            //Console.ReadLine();
        }
    }
}
