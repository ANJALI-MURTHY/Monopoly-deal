package com.game.monopolydeal.websocket;

import com.game.monopolydeal.repository.GameMasterDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.support.ChannelInterceptor;
import java.security.Principal;

import static java.lang.System.Logger.Level.*;


public class TopicSubscriptionInterceptor implements ChannelInterceptor {

        @Autowired
        GameMasterDataRepo gameDataRepo;

        private static final System.Logger LOGGER = System.getLogger("c.f.b.DefaultLogger");

        @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
            LOGGER.log(INFO, "INSIDE preSend");
        StompHeaderAccessor headerAccessor= StompHeaderAccessor.wrap(message);
        if (StompCommand.SUBSCRIBE.equals(headerAccessor.getCommand())) {
            Principal userPrincipal = headerAccessor.getUser();
            if (!validateSubscription(userPrincipal, headerAccessor.getDestination())) {
                throw new IllegalArgumentException("No permission for this topic");
            }
        }
        return message;
    }

    private boolean validateSubscription(Principal principal, String topicDestination) {
        if (principal == null) {
            // Unauthenticated user
            return false;
        }

        LOGGER.log(INFO, "Validate subscription for " + principal.getName() +  " to topic " + topicDestination);
        // Additional validation logic coming here
        return true;
    }
}