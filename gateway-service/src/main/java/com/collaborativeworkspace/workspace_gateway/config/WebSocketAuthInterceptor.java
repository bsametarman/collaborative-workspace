package com.collaborativeworkspace.workspace_gateway.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import com.collaborativeworkspace.workspace_gateway.security.JwtValidator;

@Component
public class WebSocketAuthInterceptor implements ChannelInterceptor {
	private final JwtValidator jwtValidator;

    public WebSocketAuthInterceptor(JwtValidator jwtValidator) {
        this.jwtValidator = jwtValidator;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String authHeader = accessor.getFirstNativeHeader("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                
                if (jwtValidator.validateToken(token)) {
                    String username = jwtValidator.extractUsername(token);
                    
                    UsernamePasswordAuthenticationToken authentication = 
                            new UsernamePasswordAuthenticationToken(username, null, null);
                    accessor.setUser(authentication);
                } else {
                    throw new IllegalArgumentException("Invalid JWT Token!");
                }
            } else {
                throw new IllegalArgumentException("Missing JWT Token!");
            }
        }
        return message;
    }
}
