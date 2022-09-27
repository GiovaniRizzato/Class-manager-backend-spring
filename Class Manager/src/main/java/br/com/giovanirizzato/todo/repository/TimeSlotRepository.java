package br.com.giovanirizzato.todo.repository;

import java.time.DayOfWeek;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.giovanirizzato.todo.domain.TimeSlot;


public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {

	public Set<TimeSlot> findByDay(DayOfWeek day);
}