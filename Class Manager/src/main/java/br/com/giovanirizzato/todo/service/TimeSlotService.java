package br.com.giovanirizzato.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		return repository.findAll();
	}
	
	public TimeSlot insert(TimeSlot inserido){
		inserido.setId(null);
		return repository.save(inserido);
	}
	
	public TimeSlot edit(int id, TimeSlot dto) {
		return repository.save(this.change(dto, this.findById(id)));
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
}
