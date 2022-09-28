package br.com.giovanirizzato.todo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.giovanirizzato.todo.domain.DTO.TimeSlotGetDTO;
import br.com.giovanirizzato.todo.domain.DTO.TimeSlotSetDTO;
import br.com.giovanirizzato.todo.service.TimeSlotService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/timeslot")
@ApiOperation(value = "TimeSlots")
public class TimeSlotResource {

	@Autowired
	private TimeSlotService service;

	@GetMapping
	@ApiOperation(value = "Show all the TimeSlots")
	public ResponseEntity<List<TimeSlotGetDTO>> getAllTimeSlot() {
		return ResponseEntity.ok().body(service.findAll().stream().map(TimeSlotGetDTO::new).toList());
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna TimeSlot com 'Id'")
	public ResponseEntity<TimeSlotGetDTO> getTimeSlotById(@PathVariable("id") final int id) {
		return ResponseEntity.ok().body(new TimeSlotGetDTO(service.findById(id)));
	}

	@PostMapping
	@ApiOperation(value = "Insere TimeSlot novo")
	public ResponseEntity<TimeSlotGetDTO> newTimeSlot(@RequestBody TimeSlotSetDTO dto) {
		return ResponseEntity.ok().body(new TimeSlotGetDTO(service.insert(dto.toTimeSlot())));
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Insere TimeSlot novo")
	public ResponseEntity<TimeSlotGetDTO> setTimeSlot(@PathVariable("id") final int id,
			@RequestBody TimeSlotSetDTO dto) {
		return ResponseEntity.ok().body(new TimeSlotGetDTO(service.edit(id, dto.toTimeSlot())));
	}
}