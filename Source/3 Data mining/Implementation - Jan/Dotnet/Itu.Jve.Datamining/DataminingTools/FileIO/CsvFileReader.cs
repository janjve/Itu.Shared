using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;


namespace DataminingTools.FileIO
{
    public class CsvFileReader
    {
        public static List<List<string>> ReadDataFile(string path)
        {
            List<List<string>> listA = new List<List<string>>();
            try
            {
                var reader = new StreamReader(File.OpenRead(path));


                while (!reader.EndOfStream)
                {
                    var line = reader.ReadLine();
                    var values = line.Split(';');

                    listA.Add(new List<string>(values));
                }
            }
            catch (Exception e)
            {
                throw new FileLoadException("Could not load exception", e);
            }
            return listA;
        }
    }
}
