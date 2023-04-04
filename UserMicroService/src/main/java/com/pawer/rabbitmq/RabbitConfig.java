package com.pawer.rabbitmq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    // exchange
    private String exchangeDirect = "exchange-direct";
    private String exchangeFanout = "exchange-fanout";
    private String exchangeTopic = "exchange-topic";


    // Key
    private String bindingKeyCreateUser = "binding-key-create-user";
    private String bindingKeyCreatePost = "binding-key-create-post";
    private String bindingKeyUpdateUser = "binding-key-update-user";



    // Queu
    private String queueCreateUser = "queue-create-user";
    private String queueCreatePost = "queue-create-post";
    private String queueUpdateUser= "queue-update-user";




    /**
     * ---- Exchange ----
     */
    @Bean
    DirectExchange exchangeDirect() {
        return new DirectExchange(exchangeDirect);
    }

    @Bean
    FanoutExchange exchangeFanout() {
        return new FanoutExchange(exchangeFanout);
    }

    @Bean
    TopicExchange exchangeTopic() {
        return new TopicExchange(exchangeTopic);
    }


    /**
     * ---- Queue ----
     */
    @Bean
    Queue queueSaveUser() {
        return new Queue(queueCreateUser);
    }
    @Bean
    Queue queueCreatePost(){return new Queue(queueCreatePost);}
    @Bean
    Queue queueUpdateUser(){return new Queue(queueUpdateUser);}


    /**
     * ---- Binding ----
     */
    @Bean
    public Binding bindingCreateUser(final Queue queueSaveUser, final DirectExchange directExchange) {
        return BindingBuilder.bind(queueSaveUser).to(directExchange).with(bindingKeyCreateUser);
    }
    @Bean
    public Binding bindingCreatePost(final Queue queueCreatePost, final DirectExchange directExchange) {
        return BindingBuilder.bind(queueCreatePost).to(directExchange).with(bindingKeyCreatePost);
    }
    @Bean
    public Binding bindingUpdateUser(final Queue queueUpdateUser, final DirectExchange directExchange) {
        return BindingBuilder.bind(queueUpdateUser).to(directExchange).with(bindingKeyUpdateUser);
    }


}