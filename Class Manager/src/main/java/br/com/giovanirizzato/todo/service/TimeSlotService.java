package br.com.giovanirizzato.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.giovanirizzato.todo.domain.TimeSlot;
import br.com.giovanirizzato.todo.repository.TimeSlotRepository;

@Service
public class TimeSlotService {
	
	@Autowired
	private TimeSlotRepository repository;
	
	public TimeSlot findById(int id){
		return repository.findById(id).orElseThrow();
	}
	
	public List<TimeSlot> findAll(){
		return repository.findAll(Sort.by("day").and(Sort.by("beginning")));
	}
	
	public TimeSlot insert(TimeSlot inserted){
		inserted.setId(null);
		
		if(this.checkIfNewTimeSlotIsConflicting(inserted))
			throw new IllegalStateException();
		
		return repository.save(inserted);
	}
	
	public TimeSlot edit(int id, TimeSlot dto) {
		final TimeSlot edited = this.change(dto, this.findById(id));
		
		if(this.checkIfNewTimeSlotIsConflicting(edited))
			throw new IllegalStateException();
		
		return repository.save(edited);
	}
	
	private TimeSlot change(TimeSlot dto, TimeSlot old) {
		dto.setId(old.getId());
		
		if(dto.getDay() == null)
			dto.setDay(old.getDay());
		if(dto.getBeginning() == null)
			dto.setBeginning(old.getBeginning());
		if(dto.getEnding() == null)
			dto.setEnding(old.getEnding());
		
		return dto;
	}
	
	private boolean checkIfNewTimeSlotIsConflicting(TimeSlot dto) {
		for(TimeSlot timeSlot : this.repository.findByDay(dto.getDay())) {
			if(timeSlot.getId().equals(dto.getId()))
				continue;
			
			if(!timeSlot.checkConflict(dto))
				return true;
		}
		
		return false;
	}
}
