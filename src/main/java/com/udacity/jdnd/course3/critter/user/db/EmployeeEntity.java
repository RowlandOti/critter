package com.udacity.jdnd.course3.critter.user.db;

import com.udacity.jdnd.course3.critter.schedule.db.ScheduleEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/**
 * Represents the form that employee request and response data takes.  Maps
 * to the database directly.
 */
@Entity
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue
    private long id;

    //use "org.hibernate.type.StringNVarcharType")
    @Nationalized
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "skills")
    @CollectionTable(name = "skills", joinColumns = @JoinColumn(name = "employee_id"))
    private Set<EmployeeSkill> skills;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "days")
    @CollectionTable(name = "days", joinColumns = @JoinColumn(name = "employee_id"))
    private Set<DayOfWeek> daysAvailable;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = ScheduleEntity.class, fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(referencedColumnName = "id", name = "employee_id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id", name = "schedule_id"))
    private List<ScheduleEntity> schedules;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public List<ScheduleEntity> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ScheduleEntity> schedules) {
        this.schedules = schedules;
    }
}
