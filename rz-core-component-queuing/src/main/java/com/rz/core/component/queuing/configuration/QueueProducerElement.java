package com.rz.core.component.queuing.configuration;

import com.rz.core.component.queuing.ReliabilityLevel;

import lombok.Data;

@Data
public class QueueProducerElement {
    public String id;
    public String brokerId;
    public ReliabilityLevel reliabilityLevel;
    public String exchangeName;
    public String routingKey;
    public int maxFrequencyPerSecond;
    public boolean isPersistent;
}
