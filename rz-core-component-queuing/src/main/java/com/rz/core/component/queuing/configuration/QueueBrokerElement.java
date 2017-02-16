package com.rz.core.component.queuing.configuration;

import lombok.Data;

@Data
public class QueueBrokerElement {
    private String id;
    public String address;
    public int port;
    public String virtualHost;
    public String userName;
    public String password;
    public int producerConnectionCount;
    public int producerChannelCount;
    public int publishTimeoutMilliseconds;
}
