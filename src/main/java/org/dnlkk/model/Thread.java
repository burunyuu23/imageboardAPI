package org.dnlkk.model;

import com.dnlkk.repository.annotations.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Table("thread_table")
public class Thread {
    @PK
    private Integer id;

    @FK
    @ManyToOne("threads")
    @Column("board_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Board board;

    @Column("name")
    private String name;

    @Column("created_date")
    private Timestamp createdDate = Timestamp.valueOf(LocalDateTime.now());

    @FK
    @OneToMany
    @EqualsAndHashCode.Exclude
    @JsonIncludeProperties({ "id" })
    private List<Message> messages;
}
