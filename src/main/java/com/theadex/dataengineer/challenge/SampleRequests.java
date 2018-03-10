package com.theadex.dataengineer.challenge;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

class CustomerSamples {
	public static int MAX_SAMPLE = 5000;
	private static Random rn = new Random();
	private List<Request> custReqs;
	private int count;
	
	CustomerSamples(){
		custReqs =  new ArrayList<Request>();
		count = 0;
	}
	/*
	 * Algorithm for reservoir sampling.
	 * 
	 */
	public void addRequest(Request req){
		count++;
		if(custReqs.size() < MAX_SAMPLE){
			custReqs.add(req);
		}
		else {
			int j = rn.nextInt(count);
			if (j < MAX_SAMPLE) {
				custReqs.set(j, req);
			}
		}
	}
	
	public List<Request> getRequests(){
		return custReqs;
	}
}

public class SampleRequests implements SamplerInterface {
	
	@Override
	public List<Request> sampleRequests(String pathToJsonLogFile) {
		List<Request> allReqs = new ArrayList<Request>();
		HashMap<Integer, CustomerSamples> custMap = new HashMap<Integer, CustomerSamples>();
		
		
		try {
			File file = new File(pathToJsonLogFile);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				Request req = parseJson(line);
				if(req != null){
//					System.out.println(req.toString());
					int custId = req.getCustomerID();
					
					CustomerSamples css = custMap.get(custId);
					if(css == null) {
						css = new CustomerSamples();
					}
					css.addRequest(req);
					custMap.put(custId, css);
				}
			}
			fileReader.close();
		} catch (IOException e) {
			System.err.println("Error while reading file : " + e);
		}
		
		Set<Integer> customers = custMap.keySet();
		for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
			Integer cust = (Integer) iterator.next();
			allReqs.addAll(custMap.get(cust).getRequests()
					);
		}
		
		return allReqs;
	}
	
	/**
	 * Assumptions:
	 * ------------
	 * 1. Each line contains the complete json string, and the string is not split across lines.
	 * 
	 */
	public static void main(String[] args) {
		if(args.length < 1){
			System.out.println("Usage: java -jar target/challenge-request-sampling-1.0.jar the-requests.json.log");
			return;
		}
		String filePath = args[0];
		//System.out.println("Starting sampler on file ... " + filePath);

		gson = new Gson();
		
		SampleRequests sr = new SampleRequests();
		List<Request> reqs = sr.sampleRequests(filePath);
		for (Request req : reqs) {
			if(req != null){
				System.out.println(gson.toJson(req));
			}
		}
		
	}
	
	static Gson gson;
	private Request parseJson(String jsonInString){
		Request req = null;
		try {
			req = (Request)gson.fromJson(jsonInString, Request.class);
		}
		catch(JsonSyntaxException jse){
			System.err.println("Error parsing string : " + jsonInString + "\n " + jse );
		}
		return req;
	}

}
