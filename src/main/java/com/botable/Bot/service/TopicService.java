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

    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public Topic addMessage(Long topicId, Message message) {
        Topic topic = topicRepository.findById(topicId).orElseThrow();

        message.setTopic(topic);
        topic.getMessages().add(message);

        messageRepository.save(message);
        return topicRepository.save(topic);
    }

    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    public Topic getTopic(Long id) {
        return topicRepository.findById(id).orElseThrow();
    }
}
