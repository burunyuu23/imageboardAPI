package org.dnlkk.model;

import com.dnlkk.repository.annotations.entity.Column;
import com.dnlkk.repository.annotations.entity.ManyToOne;
import com.dnlkk.repository.annotations.entity.PK;
import com.dnlkk.repository.annotations.entity.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Table("attachment_table")
public class Attachment {
    @PK
    @EqualsAndHashCode.Include
    private int id;

    @Column("msg_id")
    @ManyToOne("attachments")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    private Message message;

    @Column("attachment")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private byte[] attachment;
}
