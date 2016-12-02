package com.rz.core.practice.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.util.StringUtils;

@Configuration
public class PracticeConfig {	
	private static final String ENV_KEY = "spring.profiles.active";
	
	@Autowired
	private StandardEnvironment standardEnvironment;

	@Value("${elasticsearch.hostName}")
	private String elasticsearchHostName;

	@Value("${elasticsearch.port}")
	private int elasticsearchPort;

	@Value("${elasticsearch.clusterName}")
	private String elasticsearchClusterName;

	@Bean
	public Client elasticsearchClient() {
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

	@Bean
	public Map<String, String> properties() {
		String activeProfile = this.standardEnvironment.getProperty(ENV_KEY);
		
		Map<String, String> map = new HashMap<String, String>();
		System.out.println("application-" + activeProfile + ".properties");
		String propertySourcesName = "applicationConfig: [classpath:/application-" + activeProfile + ".properties]";
		for (PropertySource<?> propertySources : this.standardEnvironment.getPropertySources()) {
			if (true == StringUtils.endsWithIgnoreCase(propertySourcesName, propertySources.getName())
					&& propertySources.getSource() instanceof Properties) {
				Properties properties = (Properties) propertySources.getSource();

				for (Entry<Object, Object> property : properties.entrySet()) {
					map.put(property.getKey().toString(), property.getValue().toString());
				}
			}
		}

		return map;
	}
}
