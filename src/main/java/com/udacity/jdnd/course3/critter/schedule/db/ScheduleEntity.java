package com.udacity.jdnd.course3.critter.schedule.db;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Represents the form that schedule request and response data takes. Maps
 * to the database directly.
 */
@Entity
@Table(name = "schedule")
public class ScheduleEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column
    @ElementCollection(targetClass=Integer.class)
    private List<Long> employeeIds;

    @Column
    @ElementCollection(targetClass=Integer.class)
    private List<Long> petIds;

    private LocalDate date;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "skills", joinColumns = @JoinColumn(name = "employee_id"))
    private Set<EmployeeSkill> activities;

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public List<Long> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
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
}
