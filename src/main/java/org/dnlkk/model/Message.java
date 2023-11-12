package org.dnlkk.model;

import com.dnlkk.repository.annotations.entity.*;
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
}
