package com.botable.Bot.controller;

import com.botable.Bot.domain.Message;
import com.botable.Bot.domain.Topic;
import com.botable.Bot.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    // Принимаем сразу Topic
    @PostMapping
    public Topic createTopic(@RequestBody Topic topic) {
        return topicService.createTopic(topic);
    }

    // Принимаем сразу Message, и связываем его с topicId
    @PostMapping("/{topicId}/messages")
    public Topic addMessage(@PathVariable Long topicId, @RequestBody Message message) {
        return topicService.addMessage(topicId, message);
    }

    @GetMapping
    public List<Topic> findAll() {
        return topicService.findAll();
    }

    @GetMapping("/{id}")
    public Topic findOne(@PathVariable Long id) {
        return topicService.getTopic(id);
    }
}
