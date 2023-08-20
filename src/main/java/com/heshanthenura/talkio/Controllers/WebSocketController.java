package com.heshanthenura.talkio.Controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

@Controller
public class WebSocketController {

    Logger infoLogger = Logger.getLogger("Information");

    @MessageMapping("/hello")
    @SendTo("/topic/hello")
    public String greeting() {
        infoLogger.info("Request Came");
        return "Request Came";
    }
}
