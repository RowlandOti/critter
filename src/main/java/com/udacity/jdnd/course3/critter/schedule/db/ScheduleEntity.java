package com.udacity.jdnd.course3.critter.schedule.db;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.db.EmployeeEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Represents the form that schedule request and response data takes. Maps
 * to the database directly.
 */
@Entity
@Table(name = "schedule")
public class ScheduleEntity implements Serializable {

    private static final long serialVersionUID = 200L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(mappedBy = "schedules", targetEntity = EmployeeEntity.class)
    private List<EmployeeEntity> employees = new ArrayList<>();

    @ManyToMany(mappedBy = "schedules", targetEntity = PetEntity.class)
    private List<PetEntity> pets = new ArrayList<>();

    private LocalDate date;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "schedule_skills", joinColumns = @JoinColumn(name = "employee_id"))
    private Set<EmployeeSkill> activities;

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeEntity> employees) {
        this.employees = employees;
    }

    public List<PetEntity> getPets() {
        return pets;
    }

    public void setPets(List<PetEntity> pets) {
        this.pets = pets;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
