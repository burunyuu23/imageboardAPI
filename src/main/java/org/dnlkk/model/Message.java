package org.dnlkk.model;

import com.dnlkk.repository.annotations.entity.*;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Table("message_table")
public class Message extends CreatedDate {
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

    @FK
    @OneToMany
    @EqualsAndHashCode.Exclude
    private List<Attachment> attachments;

    @FK
    @OneToMany
    @EqualsAndHashCode.Exclude
    private List<Reply> replies;

    @FK
    @OneToMany
    @EqualsAndHashCode.Exclude
    private List<Reply> responses;
}
