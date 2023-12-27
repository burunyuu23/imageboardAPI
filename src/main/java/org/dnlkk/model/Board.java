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

    @With(value = """
            coalesce((
                select COUNT(message_table.id) OVER (PARTITION BY tt.board_id)
                from thread_table tt left join message_table on message_table.thread_id = tt.id
                where tt.board_id = board_table.id
                limit 1
            ),0)
            """, include = {"Theme"})
    @Column("all_messages")
    private Long allMessages;

    @With(value = """
            coalesce((
                select COUNT(
                            CASE WHEN message_table.created_date >= (CURRENT_TIMESTAMP - INTERVAL '24 hour')
                            THEN message_table.id END
                       ) OVER (PARTITION BY tt.board_id)
                from thread_table tt left join message_table on message_table.thread_id = tt.id
                where tt.board_id = board_table.id
                limit 1
            ),0)
            """, include = {"Theme"})
    @Column("today_messages")
    private Long todayMessages;
}
