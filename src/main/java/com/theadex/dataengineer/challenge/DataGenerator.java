package com.theadex.dataengineer.challenge;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import com.google.gson.Gson;
/*
 * To generate random data for testing.
 * 
 */
public class DataGenerator {

	public static String[] USER_AGENTS = {"Firefox", "Chrome", "Safari"};
	public static void main(String[] args) throws IOException {
		String filePath = "/tmp/requests.json.log.2";
		int NO_REQS = 100000;
		int NO_OF_CUST = 100;
		
		Random rn = new Random();
		Gson gson = new Gson();
		
		FileWriter fw = new FileWriter(filePath);
		for (int i = 1; i <= NO_OF_CUST; i++) {
			for(int j = 1; j <= NO_REQS; j++) {
				Request req = new Request( j, "userID-"+rn.nextInt(1000) , i,
										USER_AGENTS[rn.nextInt(USER_AGENTS.length)], "http://theadex.com", System.currentTimeMillis());
				fw.write(gson.toJson(req)+"\n");
			}
		}
		fw.close();
	}

}
