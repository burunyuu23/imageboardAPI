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

    @With("""
            COUNT(message_table.id)
            OVER (PARTITION BY thread_table.id)
            """)
    @Column("count_all")
    private Long countAll;

    @With("""
            COUNT(
                CASE WHEN message_table.created_date >= (CURRENT_TIMESTAMP - INTERVAL '24 hour')
                THEN message_table.id END
                )
            OVER (PARTITION BY thread_table.id)
            """)
    @Column("count_today")
    private Long countToday;
}
