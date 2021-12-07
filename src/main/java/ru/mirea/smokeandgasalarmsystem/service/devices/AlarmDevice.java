package ru.mirea.smokeandgasalarmsystem.service.devices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mirea.smokeandgasalarmsystem.exception.AlarmInfoNotFoundException;
import ru.mirea.smokeandgasalarmsystem.mapper.AlarmInfoMapper;
import ru.mirea.smokeandgasalarmsystem.model.domain.AlarmInfo;
import ru.mirea.smokeandgasalarmsystem.model.entity.AlarmInfoEntity;
import ru.mirea.smokeandgasalarmsystem.model.entity.dao.AlarmInfoDao;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static ru.mirea.smokeandgasalarmsystem.config.AppConstants.*;

@Component
public class AlarmDevice {
    @Autowired
    private AlarmInfoDao alarmInfoDao;

    public void enableGasAlarm(String message) {
        AlarmInfo alarmInfo = AlarmInfo.builder()
                .id(0L)
                .device(TOPIC_GAS_SENSOR)
                .description(message)
                .resolveStatus(RESOLVE_STATUS_NO)
                .timestamp(Date.from(Instant.now()))
                .build();
        AlarmInfoEntity alarmInfoEntity = AlarmInfoMapper.ALARM_INFO_MAPPER.domainToEntity(alarmInfo);
        alarmInfoDao.save(alarmInfoEntity);
    }

    public void enableSmokeAlarm(String message) {
        AlarmInfo alarmInfo = AlarmInfo.builder()
                .id(0L)
                .device(TOPIC_SMOKE_SENSOR)
                .description(message)
                .resolveStatus(RESOLVE_STATUS_NO)
                .timestamp(Date.from(Instant.now()))
                .build();
        AlarmInfoEntity alarmInfoEntity = AlarmInfoMapper.ALARM_INFO_MAPPER.domainToEntity(alarmInfo);
        alarmInfoDao.save(alarmInfoEntity);
    }

    public List<AlarmInfo> getAlarmInfo() throws AlarmInfoNotFoundException {
        List<AlarmInfoEntity> alarmInfoEntityList = alarmInfoDao.findAll();
        List<AlarmInfo> alarmInfoList = new ArrayList<>();
        if (!alarmInfoEntityList.isEmpty()) {
            for (AlarmInfoEntity alarmInfoEntity : alarmInfoEntityList) {
                alarmInfoList.add(AlarmInfoMapper.ALARM_INFO_MAPPER.entityToDomain(alarmInfoEntity));
            }
            return alarmInfoList;
        } else {
            throw new AlarmInfoNotFoundException();
        }
    }

    public AlarmInfo resolveAlarm(Long id) throws AlarmInfoNotFoundException {
        Optional<AlarmInfoEntity> alarmInfoEntity = alarmInfoDao.findById(id);
        if (alarmInfoEntity.isPresent()) {
            AlarmInfo alarmInfo = AlarmInfoMapper.ALARM_INFO_MAPPER.entityToDomain(alarmInfoEntity.get());
            alarmInfo.setResolveStatus(RESOLVE_STATUS_OK);
            return AlarmInfoMapper.ALARM_INFO_MAPPER.entityToDomain(alarmInfoDao.save(AlarmInfoMapper.ALARM_INFO_MAPPER.domainToEntity(alarmInfo)));
        } else {
            throw new AlarmInfoNotFoundException();
        }
    }

}
