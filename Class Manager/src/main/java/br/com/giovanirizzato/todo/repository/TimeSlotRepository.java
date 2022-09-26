package br.com.giovanirizzato.todo.repository;

import java.sql.Time;
import java.util.List;
import java.util.Set;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.giovanirizzato.todo.domain.TimeSlot;


public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {
	
    List<TimeSlot> findById(int Id);
}