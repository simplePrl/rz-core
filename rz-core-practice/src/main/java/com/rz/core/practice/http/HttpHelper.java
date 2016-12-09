package com.rz.core.practice.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpHelper {
	
	public static void main(String[] args){
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet("http://yz.notify.yeshj.com/push/v3/readme");
		
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);			
			HttpEntity httpEntity = httpResponse.getEntity();
			System.out.println("Response content: " + EntityUtils.toString(httpEntity, "UTF-8")); 
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
