package com.mongosi.demo.configuration;

import com.mongosi.demo.endpoint.PosLogToCustomerConverter;
import com.mongosi.demo.entity.PosLog;
import com.mongosi.demo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Transformers;

import org.springframework.integration.mongodb.outbound.MongoDbStoringMessageHandler;
import org.springframework.messaging.MessageHandler;

@Configuration
public class InfrastructureConfiguration {

    @MessagingGateway
    public interface MongoService {

        @Gateway(requestChannel = "saveCustomerDetails.input")
        void order(PosLog posLog);
    }

    @Bean
    @Autowired
    public IntegrationFlow saveCustomerDetails(MongoDbFactory mongo) {
        return f -> f
                .transform(Transformers.converter(posLogToCustomerConverter()))
                .handle(mongoOutboundAdapter(mongo));
    }

    @Bean
    public Converter<PosLog, Customer> posLogToCustomerConverter() {
        return new PosLogToCustomerConverter();
    }

    @Bean
    @Autowired
    public MessageHandler mongoOutboundAdapter(MongoDbFactory mongo) {
        MongoDbStoringMessageHandler mongoHandler = new MongoDbStoringMessageHandler(mongo);
        mongoHandler.setCollectionNameExpression(new LiteralExpression("TasCustomer"));
        return mongoHandler;
    }
}
