package com.example.plates.validators;

public enum MessageType {
    ERROR("alert alert-block alert-danger fade in"), WARNING("alert alert-warning fade in"), MESSAGE("alert alert-success fade in");

    private final String messageType;

    private MessageType(String type){
        this.messageType = type;
    }

    public String getMessageType(){
        return messageType;
    }

}
