using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LeagueOfLegendsApi.Constants
{
    public static class LeagueMinerConstants
    {
        public static string API_KEY { get { return "27e3d00f-586c-4242-9a94-af2d082d04ea"; } }
        public static string LoLEndpoint { get { return "https://euw.api.pvp.net/"; } }
        public static string MatchListEndpoint { get { return "api/lol/{0}/v2.2/matchlist/by-summoner/{1}"; } }
        public static string MatchEndpoint { get { "/api/lol/{0}/v2.2/match/{1}"; } }
        public static string LeagueEndpoint { get { "/api/lol/{0}/v2.5/league/by-summoner/{1}"; } }
    }
}
