package com.niit.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //enables broker based stomp messaging
@ComponentScan(basePackages="com.niit")
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		System.out.println("REGISTERING STOMP END POINTS");
		registry.addEndpoint("/portfolio").withSockJS();
		
	}
	
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureClientOutboundChannel(ChannelRegistration registration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		System.out.println("CONFIGURING MESSAGE BROKER");
		//to send messages from spring controller to client
		registry.enableSimpleBroker("/queue","/topic/");
		
		//to send messages from client to spring controller
		registry.setApplicationDestinationPrefixes("/app");
	}
	
	

	
	
}
