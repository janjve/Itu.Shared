using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using LeagueOfLegendsApi.Constants;
namespace LeagueOfLegendsApi
{
    public class LeagueMiner
    {
        public List<int> GetMatchList(int summonerId)
        {
            string url = String.Format(LeagueMinerConstants.LoLEndpoint + MatchListEndpoint, LeagueMinerConstants.); 
            var webRequest = new WebRequest.Create( + );
        }
    }
}
