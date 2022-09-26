package br.com.giovanirizzato.todo.resource;

import org.springframework.web.bind.annotation.*;

import br.com.giovanirizzato.todo.domain.TimeSlot;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/timeslot")
@Api(value = "Question")
public class TimeSlotResource {

    private List<TimeSlot> times;

    public TimeSlotResource() {
    	this.times = new ArrayList<>();
    	this.times.add(new TimeSlot(DayOfWeek.MONDAY, LocalTime.of(3, 30, 0),LocalTime.of(4, 30, 0)));
    	this.times.add(new TimeSlot(DayOfWeek.MONDAY, LocalTime.of(4, 30, 0),LocalTime.of(5, 30, 0)));
    	this.times.add(new TimeSlot(DayOfWeek.THURSDAY, LocalTime.of(3, 30, 0),LocalTime.of(4, 30, 0)));
    }
    
    @GetMapping
    @ApiOperation(value = "Show all the TimeSlots")
    public Collection<TimeSlot> getAllTimeSlot() {
        return this.times;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna TimeSlot com 'Id'")
    public TimeSlot getTimeSlotById(@PathVariable("id") final int id) {
        return this.times.get(id);
    }
}