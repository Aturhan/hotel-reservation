package com.abdullahturhan.reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RoomIsNotAvailableException extends RuntimeException {
    public RoomIsNotAvailableException(String message) {
        super(message);
    }
}
