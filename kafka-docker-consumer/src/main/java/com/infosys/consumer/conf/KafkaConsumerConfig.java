package com.infosys.consumer.conf;

import com.infosys.consumer.KafkaErrorHandler;
import com.infosys.consumer.MessageListener;
import com.infosys.consumer.model.Subscription;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    protected static final Logger logger = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Bean
    public MessageListener messageListener() {
        return new MessageListener();
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Subscription> subscriptionListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Subscription> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setErrorHandler(new KafkaErrorHandler());
        return factory;
    }

    public ConsumerFactory<String, Subscription> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        return new DefaultKafkaConsumerFactory<String, Subscription>(props, new StringDeserializer(), new JsonDeserializer<>(Subscription.class, false));
    }

}
