package br.com.giovanirizzato.todo.domain.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.giovanirizzato.todo.domain.TimeSlot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class TimeSlotGetDTO {
    
    private int id;
    private DayOfWeek day;
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime beginning;
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime ending;
    
    public TimeSlotGetDTO(TimeSlot timeSlot) {
    	this.id = timeSlot.getId();
    	this.day = timeSlot.getDay();
    	this.beginning = timeSlot.getBeginning();
    	this.ending = timeSlot.getEnding();
    }
}