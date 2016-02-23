using System.IO;

namespace DataminingConsole.Processes.DataMiningSpring2016.Common
{
    public class LogWriter
    {
        public static void WriteLogToFile(string path, string output, bool append = true)
        {
            //if (!File.Exists(path))
            {
                //File.Create(path);
            }
            var file = new StreamWriter(path, append);
            file.WriteLine(output);
            file.Close();
        }
    }
}
