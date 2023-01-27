package edu.bbte.idde.vbim2101.spring.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "queryjpa")
public class Query extends BaseEntity {
    private String queryString;
    private Timestamp date;
}
