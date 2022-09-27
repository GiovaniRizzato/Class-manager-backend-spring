package br.com.giovanirizzato.todo.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@Table(name="domain_timeslot")
public class TimeSlot {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @Enumerated(EnumType.STRING)
    private DayOfWeek day;
    
    private LocalTime beginning;
    private LocalTime ending;
    
    public TimeSlot(DayOfWeek day, LocalTime beginning, LocalTime ending) {
    	if(beginning.isAfter(ending))
    		throw new IllegalArgumentException("timeslot.invalidState.beginningAfterEnding");
    	
    	this.day = day;
    	this.beginning = beginning;
    	this.ending = ending;
    }
    
    public void setBeginning(LocalTime beginning) {
    	if(this.ending != null && beginning.isAfter(this.ending))
    		throw new IllegalArgumentException("timeslot.invalidState.beginningAfterEnding");
    	
    	this.beginning = beginning;
    }
    
    public void setEnding(LocalTime ending) {
    	if(this.beginning != null && this.beginning.isAfter(ending))
    		throw new IllegalArgumentException("timeslot.invalidState.beginningAfterEnding");
    	
    	this.ending = ending;
    }
    
    public Duration getDuration() {
    	return Duration.between(this.beginning, this.ending);
    }
    
    public boolean checkConflict(TimeSlot other) {
    	if(this.day != other.day)
    		return false;
    	
    	//It's not consider a conflict if starts exatly as thr "other" ends and vice-versa
    	boolean endsBeforeThis = other.ending.isBefore(this.beginning) && !this.beginning.equals(other.ending);
    	boolean beginsAfterThis = other.beginning.isAfter(this.ending) && !this.ending.equals(other.beginning);
    	return endsBeforeThis || beginsAfterThis;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("(");
    	sb.append(this.day);
    	sb.append(") ");
    	sb.append(this.beginning);
    	sb.append(" ~ ");
    	sb.append(this.ending);
		return sb.toString();
    }
}