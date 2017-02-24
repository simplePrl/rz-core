package com.rz.core.component.queuing.configuration;

import com.rz.core.component.queuing.ReliabilityLevel;

import lombok.Data;

@Data
public class QueueProducerElement {
    private String id;
    private String brokerId;
    private ReliabilityLevel reliabilityLevel;
    private String exchangeName;
    private String routingKey;
    private int maxFrequencyPerSecond;
    private boolean needPersistent;
}
