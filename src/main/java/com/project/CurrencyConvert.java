package com.project;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CurrencyConvert
{

	public static void main(String[] args) throws IOException 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Type currency to covert from :");
		String convertFrom = sc.nextLine();
		System.out.println("Type currency to covert to :");
		String convertTo = sc.nextLine();
		
		System.out.println("Type quantity to convert :");
		BigDecimal quantity = sc.nextBigDecimal();
		
		String urlString ="http://api.exchangerate.host/latest?base="+convertFrom.toUpperCase();
		
		OkHttpClient client=new OkHttpClient();
		Request request=new Request.Builder()
				.url(urlString)
				.get().build();
		
		Response response = client.newCall(request).execute();
		String stringResponse = response.body().string();
		
		JSONObject jsonObject = new JSONObject(stringResponse);
		JSONObject rateObject = jsonObject.getJSONObject("rates");
		BigDecimal rate = rateObject.getBigDecimal(convertTo.toUpperCase());
		
		BigDecimal result =rate.multiply(quantity);
		System.out.println(result);
		
	}


}
