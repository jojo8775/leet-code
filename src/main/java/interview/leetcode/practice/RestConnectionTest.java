package interview.leetcode.practice;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class RestConnectionTest {
	public static void main(String[] args) {
		serialization();
	}

//	public static int getTotalGoals_1(String team, int year) {
//		
//	}

	public static int getTotalGoals(String team, int year) {
		URL url;

		int status = 0;
		try {
			url = new URL("https://jsonmock.hackerrank.com/api/football_matches/");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			addParameters(con, team, year, 1);

			con.setRequestProperty("Content-Type", "application/json");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);

			status = con.getResponseCode();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return status;
	}

	private static void addParameters(HttpURLConnection con, String team, int year, int pageNumber) {
		Map<String, String> params = new HashMap<>();
		params.put("year", String.valueOf(year));
		params.put("team1", team);
		params.put("page", String.valueOf(pageNumber));

		con.setDoOutput(true);
		DataOutputStream out;
		try {
			out = new DataOutputStream(con.getOutputStream());
			out.writeBytes(getParameters(params));
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String getParameters(Map<String, String> params) {
		StringBuilder sb = new StringBuilder();

		for (Map.Entry<String, String> e : params.entrySet()) {
			sb.append(e.getKey()).append("=").append(e.getValue()).append("&");
		}

		String result = sb.toString();
		return result.length() > 0 ? result.substring(0, result.length() - 1) : result;
	}

	private static void read(HttpURLConnection con) {

		try {
			int status;
			Reader streamReader = null;
			status = con.getResponseCode();
			if (status > 299) {
				streamReader = new InputStreamReader(con.getErrorStream());
			} else {
				streamReader = new InputStreamReader(con.getInputStream());
			}

			BufferedReader in = new BufferedReader(streamReader);
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			
			System.out.println(content.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void serialization() {
		String s = "{"
				  + "\"page\": 1,"
				  + "\"per_page\": 10,"
				  + "\"total\": 6,"
				  + "\"total_pages\": 1,"
				  + "\"data\": ["
				   + "{"
				     + "\"competition\": \"UEFA Champions League\","
				     + "\"year\": 2011,"
				     + "\"round\": \"GroupH\","
				     + "\"team1\": \"Barcelona\","
				     + "\"team2\": \"AC Milan\","
				     + "\"team1goals\": \"2\","
				     + "\"team2goals\": \"2\""
				    + "},"
				   + "{"
				     + "\"competition\": \"UEFA Champions League\","
				     + "\"year\": 2011,"
				     + "\"round\": \"GroupH\","
				     + "\"team1\": \"Barcelona\","
				     + "\"team2\": \"Viktoria Plzen\","
				     + "\"team1goals\": \"2\","
				     + "\"team2goals\": \"0\""
				    + "},"
				   + "{"
				     + "\"competition\": \"UEFA Champions League\","
				     + "\"year\": 2011,"
				     + "\"round\": \"GroupH\","
				     + "\"team1\": \"Barcelona\","
				     + "\"team2\": \"BATE Borisov\","
				     + "\"team1goals\": \"4\","
				     + "\"team2goals\": \"0\""
				    + "},"
				   + "{"
				     + "\"competition\": \"UEFA Champions League\","
				     + "\"year\": 2011,"
				     + "\"round\": \"R16\","
				     + "\"team1\": \"Barcelona\","
				     + "\"team2\": \"Bayer Leverkusen\","
				     + "\"team1goals\": \"7\","
				     + "\"team2goals\": \"1\""
				    + "},"
				   + "{"
				     + "\"competition\": \"UEFA Champions League\","
				     + "\"year\": 2011,"
				     + "\"round\": \"QF\","
				     + "\"team1\": \"Barcelona\","
				     + "\"team2\": \"AC Milan\","
				     + "\"team1goals\": \"3\","
				     + "\"team2goals\": \"1\""
				    + "},"
				   + "{"
				     + "\"competition\": \"UEFA Champions League\","
				     + "\"year\": 2011,"
				     + "\"round\": \"SF\","
				     + "\"team1\": \"Barcelona\","
				     + "\"team2\": \"Chelsea\","
				     + "\"team1goals\": \"2\","
				     + "\"team2goals\": \"2\""
				    + "}"
				  + "]"
				+ "}";
		
		P p = new Gson().fromJson(s, P.class);
		
		System.out.println(p.data.size());
	}
	
	private static class P {
		public int total_pages;
		public List<E> data; 
	}
	
	private static class E{
		public String team1;
		public String team2;
		public int team1goals;
		public int team2goals;
	}
}
