package com.rz.core.component.queuing.configuration;

import com.rz.core.component.queuing.ReliabilityLevel;

import lombok.Data;

@Data
public class QueueConsumerElement {
    public String id;
    public String brokerId;
    public String queueName ;
    public int parallelCount;
    public String type;
    public ReliabilityLevel reliabilityLevel;
    public String errorQueueName;
}
