package br.com.giovanirizzato.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.giovanirizzato.todo.domain.TimeSlot;


public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {
}