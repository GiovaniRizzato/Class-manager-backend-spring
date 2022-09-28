package br.com.giovanirizzato.todo.domain.DTO;

import java.util.Set;
import br.com.giovanirizzato.todo.domain.Lesson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LessonSetDTO {
	
    private String name;
    private String description;
    private Set<Integer> timeslotsIds;
    
    public Lesson toLesson() {
    	final Lesson lesson = new Lesson();
    	lesson.setName(this.name);
    	lesson.setDescription(this.description);
    	
		return lesson;
    }
}