package ru.mirea.smokeandgasalarmsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.mirea.smokeandgasalarmsystem.model.domain.AlarmInfo;
import ru.mirea.smokeandgasalarmsystem.model.entity.AlarmInfoEntity;

@Mapper
public interface AlarmInfoMapper {
    AlarmInfoMapper ALARM_INFO_MAPPER = Mappers.getMapper(AlarmInfoMapper.class);

    AlarmInfoEntity domainToEntity(AlarmInfo alarmInfo);

    AlarmInfo entityToDomain(AlarmInfoEntity alarmInfoEntity);
}
