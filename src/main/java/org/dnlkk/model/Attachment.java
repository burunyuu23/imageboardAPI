package org.dnlkk.model;

import com.dnlkk.repository.annotations.entity.Column;
import com.dnlkk.repository.annotations.entity.ManyToOne;
import com.dnlkk.repository.annotations.entity.PK;
import com.dnlkk.repository.annotations.entity.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Table("attachment_table")
@NoArgsConstructor
public class Attachment {
    @PK
    @EqualsAndHashCode.Include
    private Integer id;

    @Column("msg_id")
    @ManyToOne("attachments")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    private Message message;

    @Column("attachment")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private byte[] file;

    public Attachment(Message message, byte[] file) {
        this.message = message;
        this.file = file;
    }
    public Attachment(byte[] file) {
        this.message = new Message(null);
        this.file = file;
    }
}
