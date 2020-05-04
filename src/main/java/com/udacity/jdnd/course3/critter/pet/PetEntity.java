package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.schedule.db.ScheduleEntity;
import com.udacity.jdnd.course3.critter.user.db.CustomerEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Represents the form that pet request and response data takes.  Maps
 * to the database directly.
 */
@Entity
@Table(name = "pet")
public class PetEntity implements Serializable {

    private static final long serialVersionUID = 300L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private PetType type;
    private String name;
    private LocalDate birthDate;
    private String notes;

    @ManyToOne(cascade = {CascadeType.MERGE}, targetEntity = CustomerEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToMany(targetEntity = ScheduleEntity.class, fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(referencedColumnName = "id", name = "pet_id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id", name = "schedule_id"))
    private List<ScheduleEntity> schedules;

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ScheduleEntity> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ScheduleEntity> schedules) {
        this.schedules = schedules;
    }
}
