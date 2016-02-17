using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;


namespace DataminingTools
{
    public class CsvFileReader
    {
        public static List<List<string>> ReadDataFile()
        {
            List<List<string>> listA = new List<List<string>>();
            try { 
            var reader = new StreamReader(File.OpenRead(@"Data\DataMining 2016 (Responses).csv"));
            

            while (!reader.EndOfStream)
            {
                var line = reader.ReadLine();
                var values = line.Split(';');

                listA.Add(new List<string>(values));
            }
            } catch (Exception e)
            {

            }
            return listA;
        }
    }
}
