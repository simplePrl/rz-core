package com.rz.core.practice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

@Configuration
public class PracticeConfig {
	@Value("${elasticsearch.hostName}")
	private String elasticsearchHostName;
	
	@Value("${elasticsearch.port}")
	private int elasticsearchPort;
	
	@Value("${elasticsearch.clusterName}")
	private String elasticsearchClusterName;
	
	@Bean
	public Client elasticsearchClient(){
		Settings settings = Settings.builder().put("cluster.name", this.elasticsearchClusterName).build();
		TransportClient client = TransportClient.builder().settings(settings).build();
		
		InetAddress inetAddress = null;
		try {
			inetAddress = InetAddress.getByName(this.elasticsearchHostName);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
		client.addTransportAddress(new InetSocketTransportAddress(inetAddress, this.elasticsearchPort));
		
		return client;
	}
}
