package com.heshanthenura.talkio.Services;

import org.springframework.stereotype.Service;

@Service
public class Validator {
    public static boolean isValidName(String name) {
        return name != null && !name.isEmpty() && name.length() >= 3;
    }
}
