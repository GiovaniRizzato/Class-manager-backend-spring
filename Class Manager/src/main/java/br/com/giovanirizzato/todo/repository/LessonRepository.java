package br.com.giovanirizzato.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.giovanirizzato.todo.domain.Lesson;


public interface LessonRepository extends JpaRepository<Lesson, Integer> {
}