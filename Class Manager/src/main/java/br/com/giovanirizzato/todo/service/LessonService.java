package br.com.giovanirizzato.todo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.giovanirizzato.todo.domain.Lesson;
import br.com.giovanirizzato.todo.domain.DTO.LessonSetDTO;
import br.com.giovanirizzato.todo.repository.LessonRepository;

@Service
public class LessonService {
	
	@Autowired
	private LessonRepository repository;
	
	@Autowired
	private TimeSlotService timeSlotService;
	
	public Lesson findById(int id){
		return repository.findById(id).orElseThrow();
	}
	
	public List<Lesson> findAll(){
		return this.repository.findAll();
	}
	
	public Lesson insert(LessonSetDTO setDto){
		final Lesson lesson = setDto.toLesson();
		
		lesson.setId(null);
		final Lesson created = this.repository.save(lesson);
		
		if(setDto.getTimeslotsIds() != null) {
			this.convertTimeSlotIds(created, setDto);
			return this.repository.save(created);
		}else {
			return created;
		}
	}
	
	public Lesson edit(int id, LessonSetDTO setDto) {
		final Lesson changed = this.change(setDto, this.findById(id));
		this.change(setDto, changed);
		
		return this.repository.save(changed);
	}
	
	private Lesson change(LessonSetDTO setDto, Lesson old) {
		final Lesson edited = setDto.toLesson();
		edited.setId(old.getId());
		
		if(edited.getName() == null)
			edited.setName(old.getName());
		if(edited.getDescription() == null)
			edited.setDescription(old.getDescription());
		
		this.convertTimeSlotIds(edited, setDto);
		
		return edited;
	}
	
	private void convertTimeSlotIds(Lesson lesson, LessonSetDTO setDto) {
		if(setDto.getTimeslotsIds() != null) {
			lesson.setTimeslots(setDto.getTimeslotsIds()
					.stream()
					.map(id -> this.timeSlotService.findById(id))
					.collect(Collectors.toSet()));
		}
	}
}
