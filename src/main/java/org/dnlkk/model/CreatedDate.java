package org.dnlkk.model;

import com.dnlkk.repository.annotations.entity.Column;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class CreatedDate {
    @Column("created_date")
    protected Timestamp createdDate;

    public CreatedDate() {
        this.createdDate = Timestamp.valueOf(LocalDateTime.now());
    }
}
