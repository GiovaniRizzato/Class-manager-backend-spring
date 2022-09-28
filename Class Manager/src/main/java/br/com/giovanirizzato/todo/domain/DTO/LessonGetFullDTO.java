package br.com.giovanirizzato.todo.domain.DTO;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.giovanirizzato.todo.domain.Lesson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LessonGetFullDTO {
	
    private Integer id;
    private String name;
    private String description;
    private Set<TimeSlotGetDTO> timeslots;
    
    public LessonGetFullDTO(Lesson lesson) {
    	this.id = lesson.getId();
    	this.name = lesson.getName();
    	this.description = lesson.getDescription();
    	this.timeslots = lesson.getTimeslots()
    			.stream()
    			.map(TimeSlotGetDTO :: new)
    			.collect(Collectors.toSet());
    }
}