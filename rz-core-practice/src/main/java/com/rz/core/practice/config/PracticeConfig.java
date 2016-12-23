package com.rz.core.practice.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.util.StringUtils;

import com.rz.core.practice.dynamic.AspectProxy;
import com.rz.core.practice.dynamic.AspectWork;
import com.rz.core.practice.dynamic.Worker;

@Configuration
@EnableAspectJAutoProxy
public class PracticeConfig {
    private static final String ENV_KEY = "spring.profiles.active";

    @Autowired
    private StandardEnvironment standardEnvironment;

    @Value("${elasticsearch.hostName}")
    private String elasticsearchHostName;

    @Value("${elasticsearch.port:9300}")
    private int elasticsearchPort;

    @Value("${elasticsearch.clusterName}")
    private String elasticsearchClusterName;

    @Bean
    public AspectProxy annotationBroker() {
        return new AspectProxy();
    }

    @Bean
    public Worker woker() {
        return new Worker();
    }

    @Bean
    public AspectWork userService() {
        return new AspectWork();
    }

    @Bean
    public Map<String, String> properties() {
        String activeProfile = this.standardEnvironment.getProperty(ENV_KEY);

        Map<String, String> map = new HashMap<String, String>();
        System.out.println("application-" + activeProfile + ".properties");
        String propertySourcesName = "applicationConfig: [classpath:/application-" + activeProfile + ".properties]";
        for (PropertySource<?> propertySources : this.standardEnvironment.getPropertySources()) {
            if (true == StringUtils.endsWithIgnoreCase(propertySourcesName, propertySources.getName()) && propertySources.getSource() instanceof Properties) {
                Properties properties = (Properties) propertySources.getSource();

                for (Entry<Object, Object> property : properties.entrySet()) {
                    map.put(property.getKey().toString(), property.getValue().toString());
                }
            }
        }

        return map;
    }
}
