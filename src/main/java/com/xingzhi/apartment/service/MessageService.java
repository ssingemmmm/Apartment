package com.xingzhi.apartment.service;

import com.amazonaws.services.sqs.model.Message;

import java.util.List;

public interface MessageService {
    String createQueue(String queueName);
    String getQueueUrl(String queueName);
    void sendMessage(String queueName, String msg);
    List<Message> getMessages(String queueName);
}