package hu.lenartl.shogi.controller;

import hu.lenartl.shogi.dto.in.MovementIntent;
import hu.lenartl.shogi.service.MovementService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin
public class MovementController {

    private final MovementService service;
    private final SimpMessageSendingOperations messagingTemplate;


    public MovementController(MovementService service, SimpMessageSendingOperations messagingTemplate) {
        this.service = service;
        this.messagingTemplate = messagingTemplate;
    }

    // if a message is sent to /initiate, this method is invoked
    @MessageMapping("/{uuid}")
    //broadcasts the return value to /game-status/moves
    //very useful: https://github.com/SatvikNema/satchat/blob/main/src/main/java/com/satvik/satchat/service/ChatService.java
    public void movePiece(@Payload MovementIntent intent,
                          @DestinationVariable("uuid") String uuid) {
//        https://spring.io/guides/gs/messaging-stomp-websocket
//        ValidMove move = service.movePiece(uuid, intent);

//        messagingTemplate.convertAndSend("/games/" + uuid, move);
    }

}
