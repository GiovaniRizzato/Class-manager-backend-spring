package br.com.giovanirizzato.todo.domain.DTO;

import br.com.giovanirizzato.todo.domain.Lesson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LessonGetSummaryDTO {
	
    private Integer id;
    private String name;
    private String description;
    
    public LessonGetSummaryDTO(Lesson lesson) {
    	this.id = lesson.getId();
    	this.name = lesson.getName();
    	this.description = lesson.getDescription();
    }
}