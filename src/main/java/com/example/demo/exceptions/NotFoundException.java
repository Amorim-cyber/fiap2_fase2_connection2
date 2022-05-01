package com.example.demo.exceptions;

import com.example.demo.util.MessageUtils;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super(MessageUtils.NO_RECORDS_FOUND);
    }
}
