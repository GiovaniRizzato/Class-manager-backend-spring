package br.com.giovanirizzato.todo.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

@Entity
@Table(name="domain_timeslot")
@Data
@NoArgsConstructor
public class TimeSlots {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int Id;
    
    @Enumerated(EnumType.STRING)
    private DayOfWeek day;
    
    private LocalTime beginning;
    private LocalTime ending;
    
    public TimeSlots(DayOfWeek day, LocalTime beginning, LocalTime ending) {
    	if(beginning.isAfter(ending))
    		throw new IllegalArgumentException("timeslot.invalidState.beginningAfterEnding");
    	
    	this.day = day;
    	this.beginning = beginning;
    	this.ending = ending;
    }
    
    public void setBeginning(LocalTime beginning) {
    	if(beginning.isAfter(this.ending))
    		throw new IllegalArgumentException("timeslot.invalidState.beginningAfterEnding");
    	
    	this.beginning = beginning;
    }
    
    public void setEnding(LocalTime ending) {
    	if(this.beginning.isAfter(ending))
    		throw new IllegalArgumentException("timeslot.invalidState.beginningAfterEnding");
    	
    	this.ending = ending;
    }
    
    public Duration getDuration() {
    	return Duration.between(this.beginning, this.ending);
    }
    
    public boolean checkConflict(TimeSlots other) {
    	if(this.day != other.day)
    		return false;
    	
    	//It's not consider a conflict if starts exatly as thr "other" ends and vice-versa
    	boolean endsBeforeThis = other.ending.isBefore(this.beginning) && !this.beginning.equals(other.ending);
    	boolean beginsAfterThis = other.beginning.isAfter(this.ending) && !this.ending.equals(other.beginning);
    	return endsBeforeThis || beginsAfterThis;
    }
}