package com.ricky.websocket;

/**
 * 消息接收实体
 */
public class InMessage {

    private String topic;
    private String id;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public InMessage() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
