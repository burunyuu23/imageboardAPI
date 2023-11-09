package org.dnlkk.model;

import com.dnlkk.repository.annotations.entity.*;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Table("reply_table")
@NoArgsConstructor
public class Reply {
    @PK
    @EqualsAndHashCode.Include
    private Integer id;

    @Column("reply_id")
    @ManyToOne("replies")
    @ToString.Exclude
    @JsonIncludeProperties({ "id" })
    @EqualsAndHashCode.Exclude
    private Message replyMessage;

    @Column("msg_id")
    @ManyToOne("responses")
    @ToString.Exclude
    @JsonIncludeProperties({ "id" })
    @EqualsAndHashCode.Exclude
    private Message message;

    public Reply(Message replyMessage, Message message) {
        this.replyMessage = replyMessage;
        this.message = message;
    }
}
