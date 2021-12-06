package ru.mirea.smokeandgasalarmsystem.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlarmInfo {
    private Long id;
    private String resolveStatus;
    private String device;
    private String description;
    private Date timestamp;
}
