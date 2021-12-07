package ru.mirea.smokeandgasalarmsystem.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlarmInfo {
    private Long id;
    private String device;
    private String description;
    private String resolveStatus;
    private Date timestamp;
}
