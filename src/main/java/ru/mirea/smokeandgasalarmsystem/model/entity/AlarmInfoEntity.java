package ru.mirea.smokeandgasalarmsystem.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "Alarms", schema = "public")
public class AlarmInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "resolve_status")
    private String resolveStatus;
    @Column(name = "device")
    private String device;
    @Column(name = "description")
    private String description;
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
}
