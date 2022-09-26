package br.com.giovanirizzato.todo.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="domain_class")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long Id;
    
    private String name;
    private String description;
    
    @OneToMany
    private Set<TimeSlot> timeslots = new HashSet<>();
}