package org.dnlkk.model;

import com.dnlkk.repository.annotations.entity.*;
import com.dnlkk.repository.annotations.entity.With;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Table("message_table")
public class Message {
    @PK
    @EqualsAndHashCode.Include
    private Integer id;

    @FK
    @ManyToOne("messages")
    @Column("thread_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIncludeProperties({ "id" })
    private Thread thread;

    @Column("body")
    private String body;

    @Column("created_date")
    private Timestamp createdDate = Timestamp.valueOf(LocalDateTime.now());

    @FK
    @OneToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Attachment> attachments;

    @FK
    @OneToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Reply> replies;

    @FK
    @OneToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Reply> responses;

    @With("""
            select COALESCE(COUNT(rt.id) OVER (PARTITION BY mt.id), 0) AS replies_count
            from message_table mt
            LEFT join reply_table rt ON mt.id = rt.reply_id
            where message_table.id = mt.id
            LIMIT 1
            """)
    @Column("replies_count")
    private Long repliesCount;

    public Message(Integer id) {
        this.id = id;
    }

    public Message() {
    }
}
