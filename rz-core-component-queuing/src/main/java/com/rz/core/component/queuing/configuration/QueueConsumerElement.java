package com.rz.core.component.queuing.configuration;

import com.rz.core.component.queuing.ReliabilityLevel;

import lombok.Data;

@Data
public class QueueConsumerElement {
    private String id;
    private String brokerId;
    private String queueName ;
    private int parallelCount;
    private String type;
    private ReliabilityLevel reliabilityLevel;
    private String errorQueueName;
}
