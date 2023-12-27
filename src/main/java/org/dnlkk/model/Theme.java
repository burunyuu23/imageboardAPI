package org.dnlkk.model;

import com.dnlkk.repository.annotations.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@Table("theme_table")
public class Theme {
    @PK
    private Integer id;
    private String name;
    private String description;

    @FK
    @OneToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIncludeProperties({ "id", "name", "description", "banner", "allMessages", "todayMessages" })
    private List<Board> boards;
}
