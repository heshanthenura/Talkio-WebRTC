package com.heshanthenura.talkio.Services;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IDGenerator {
    public String Generate(){
        UUID uniqueId = UUID.randomUUID();
        return uniqueId.toString();

    }
}
