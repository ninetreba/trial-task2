package com.example.trial.task.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

@AllArgsConstructor
public enum ErrorCodeEnum {
    QUOTE_NOT_FOUND("Цитата не найдена", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("Пользователь не найден", HttpStatus.NOT_FOUND),
    CANNOT_VOTE_MORE_THAN_ONCE("cannot vote more than once");


    private final String messageTemplate;
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;


    public String getMessage(Object... args){
        return MessageFormat.format(messageTemplate, args);
    }

    ErrorCodeEnum(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public String getCode(){
        return this.name();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }


}
