package ru.mirea.smokeandgasalarmsystem;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.smokeandgasalarmsystem.exception.AlarmInfoNotFoundException;
import ru.mirea.smokeandgasalarmsystem.model.AppResponse;
import ru.mirea.smokeandgasalarmsystem.model.domain.AlarmInfo;
import ru.mirea.smokeandgasalarmsystem.service.AlarmDevice;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/personal-loyalty")
@Tag(name = "Personal Loyalty System Controller")
public class AppController {

    private final AlarmDevice alarmDevice;

    public AppController(AlarmDevice alarmDevice) {
        this.alarmDevice = alarmDevice;
    }


    @GetMapping("get-alarm-info")
    @Operation(summary = "Show alarm info")
    public ResponseEntity<AppResponse<List<AlarmInfo>>> getAlarmInfo() {
        try {
            return new ResponseEntity<>(AppResponse.ok(alarmDevice.getAlarmInfo()), HttpStatus.OK);
        } catch (AlarmInfoNotFoundException e) {
            return new ResponseEntity<>(AppResponse.error(e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("resolve-alarm")
    @Operation(summary = "Resolve alarm by id")
    public ResponseEntity<AppResponse<AlarmInfo>> resolveAlarmById(@Parameter(name = "id", required = true, description = "Alarm id") @RequestParam Long id) {
        try {
            return new ResponseEntity<>(AppResponse.ok(alarmDevice.resolveAlarm(id)), HttpStatus.OK);
        } catch (AlarmInfoNotFoundException e) {
            return new ResponseEntity<>(AppResponse.error(e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

}
