package com.rz.core.component.queuing.rabbitmq;

import java.io.Closeable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RabbitMqQueueClient implements Closeable {

    @Override
    public void close() {
        log.info("");
    }
}
