package com.collaborativeworkspace.workspace_gateway.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import com.collaborativeworkspace.workspace_gateway.repository.DocumentAccessRepository;

@Component
public class WebSocketSecurityInterceptor implements ChannelInterceptor {
	private final DocumentAccessRepository documentAccessRepository;

    public WebSocketSecurityInterceptor(DocumentAccessRepository documentAccessRepository) {
        this.documentAccessRepository = documentAccessRepository;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
            String destination = accessor.getDestination();

            if (destination != null && destination.startsWith("/topic/document/")) {
                
                String documentId = destination.replace("/topic/document/", "");
                
                if (accessor.getUser() == null) {
                    throw new RuntimeException("Connection rejected: User not found.");
                }
                
                String userId = accessor.getUser().getName();
                System.out.println(userId);
                var result = documentAccessRepository.findByDocumentIdAndUserId(documentId, userId);
                System.out.println(result);
                boolean hasAccess = documentAccessRepository.existsByDocumentIdAndUserId(documentId, userId);
                
                if (!hasAccess) {
                    System.out.println("SECURITY ALERT: User " + userId + " blocked from Document " + documentId);
                    throw new RuntimeException("Access Denied: You do not have permission for this document.");
                }
            }
        }
        
        return message; 
    }
}
