package ru.mirea.smokeandgasalarmsystem.model.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.smokeandgasalarmsystem.model.entity.AlarmInfoEntity;

@Repository
public interface AlarmInfoDao extends JpaRepository<AlarmInfoEntity, Long> {
}

