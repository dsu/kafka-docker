package com.infosys.consumer;

import com.infosys.consumer.service.EmailService;
import com.infosys.consumer.model.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class MessageListener {

    protected static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    private EmailService service;

    @KafkaListener(topics = "${spring.kafka.subscription.topic}",containerFactory = "subscriptionListenerContainerFactory")
    public void listen(Subscription message) {
        System.out.println( "new message: " + message);service.sendTheEmail(message);
    }


}
