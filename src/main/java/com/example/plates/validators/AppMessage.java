package com.example.plates.validators;

public class AppMessage {
    private final String message;
    private final MessageType  messageType;
    private final Boolean exist;

    private AppMessage(MessageBuilder builder){
        this.message = builder.message;
        this.messageType = builder.messageType;
        this.exist = builder.exist;
    }

    public static class MessageBuilder{

        private final Boolean exist;
        private MessageType messageType = MessageType.MESSAGE;
        private String message = "";

        public MessageBuilder(){
            this.exist = Boolean.FALSE;
        }

        public MessageBuilder(Boolean exist){
            this.exist = exist;
        }

        public MessageBuilder messageType(MessageType messageType){
            this.messageType = messageType;
            return this;
        }

        public MessageBuilder message(String message){
            this.message = message;
            return this;
        }

        public AppMessage build(){
            return new AppMessage(this);
        }

    }

    public Boolean getExist() {
        return exist;
    }

    public String getMessage() {
        return message;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    @Override
    public String toString(){
        return message;
    }

    public String getMsgType(){
        return messageType.getMessageType();
    }

    public static AppMessage getMessage(Integer type){
        switch(type){
            case 1:
                return new AppMessage.MessageBuilder(Boolean.TRUE).message("Record saved").messageType(MessageType.MESSAGE).build();
            case 2:
                return new AppMessage.MessageBuilder(Boolean.TRUE).message("Record deleted").messageType(MessageType.MESSAGE).build();
            case 3:
                return new AppMessage.MessageBuilder(Boolean.TRUE).message("Operation cancelled").messageType(MessageType.MESSAGE).build();
            case 4:
                return new AppMessage.MessageBuilder(Boolean.TRUE).message("Password successfully changed").messageType(MessageType.MESSAGE).build();
            default:
                throw new IllegalArgumentException("Parameter provided not recognised :"+type);
        }
    }
}
