package org.dnlkk.model;

import com.dnlkk.repository.annotations.entity.*;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Table("board_table")
public class Board {
    @PK
    private String id;

    @FK
    @OneToMany
    @EqualsAndHashCode.Exclude
    @JsonIncludeProperties({ "id" })
    private List<Thread> threads;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("created_date")
    private Timestamp createdDate = Timestamp.valueOf(LocalDateTime.now());

    @FK
    @ManyToOne("boards")
    @Column("theme_id")
    private Theme theme;

    @Column("banner")
    private byte[] banner;
}
