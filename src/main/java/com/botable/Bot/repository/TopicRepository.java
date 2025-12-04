package com.botable.Bot.repository;

import com.botable.Bot.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    @Query(
            value = """
            SELECT t.*
            FROM topic t
            WHERE LOWER(:text) LIKE CONCAT('%', LOWER(t.name), '%')
            ORDER BY t.id
            LIMIT 1
        """,
            nativeQuery = true
    )
    Optional<Topic> findMatchingTopic(String text);
}
