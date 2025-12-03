package com.botable.Bot.service;

import com.botable.Bot.domain.Message;
import com.botable.Bot.domain.Topic;
import com.botable.Bot.repository.MessageRepository;
import com.botable.Bot.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final MessageRepository messageRepository;

    public Topic createTopic(String name) {
        Topic topic = new Topic(name);
        return topicRepository.save(topic);
    }

    public Topic addMessage(Long topicId, String content) {
        Topic topic = topicRepository.findById(topicId).orElseThrow();

        Message msg = new Message(content);
        msg.setTopic(topic);

        topic.getMessages().add(msg);

        messageRepository.save(msg);
        return topicRepository.save(topic);
    }

    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    public Topic getTopic(Long id) {
        return topicRepository.findById(id).orElseThrow();
    }
}
