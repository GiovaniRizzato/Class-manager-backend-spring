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

import br.com.giovanirizzato.todo.domain.DTO.LessonGetFullDTO;
import br.com.giovanirizzato.todo.domain.DTO.LessonGetSummaryDTO;
import br.com.giovanirizzato.todo.domain.DTO.LessonSetDTO;
import br.com.giovanirizzato.todo.service.LessonService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/Lesson")
@ApiOperation(value = "Lessons")
public class LessonResource {

	@Autowired
	private LessonService service;

	@GetMapping
	@ApiOperation(value = "Show all the Lessons")
	public ResponseEntity<List<LessonGetSummaryDTO>> getAllTimeSlot() {
		return ResponseEntity
			.ok()
			.body(service.findAll().stream().map(LessonGetSummaryDTO :: new).toList());
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Show the Lesson with 'Id'")
	public ResponseEntity<LessonGetFullDTO> getTimeSlotById(@PathVariable("id") final int id) {
		return ResponseEntity
				.ok()
				.body(new LessonGetFullDTO(service.findById(id)));
	}
	
	@PostMapping
	@ApiOperation(value = "Insert new Lesson")
	public ResponseEntity<LessonGetFullDTO> newTimeSlot(@RequestBody LessonSetDTO dto) {
		return ResponseEntity
				.ok()
				.body(new LessonGetFullDTO(service.insert(dto)));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Edit the Lesson with 'id'")
	public ResponseEntity<LessonGetFullDTO> setTimeSlot(@PathVariable("id") final int id, @RequestBody LessonSetDTO dto) {
		return ResponseEntity
				.ok()
				.body(new LessonGetFullDTO(service.edit(id, dto)));
	}
}