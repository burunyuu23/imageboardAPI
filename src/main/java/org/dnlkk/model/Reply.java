package org.dnlkk.model;

import com.dnlkk.repository.annotations.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Table("reply_table")
public class Reply {
    @PK
    @EqualsAndHashCode.Include
    private int id;

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
}
