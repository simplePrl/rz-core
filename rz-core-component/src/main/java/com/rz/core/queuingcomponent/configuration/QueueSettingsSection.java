package com.rz.core.queuingcomponent.configuration;

import java.util.List;

import lombok.Data;

@Data
public class QueueSettingsSection {
    public String appId;
    public boolean enableLog;
    public List<QueueBrokerElement>   queueBrokers;
    public List<QueueProducerElement> queueProducers;
    public List<QueueConsumerElement> queueConsumers;
    
}
