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
public class TimeSlotSetDTO {
    
    private DayOfWeek day;
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime beginning;
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime ending;
    
    public TimeSlot toTimeSlot() {
    	final TimeSlot novo = new TimeSlot();
    	novo.setDay(this.day);
    	novo.setBeginning(this.beginning);
    	novo.setEnding(this.ending);
    	
    	return novo;
    }
}