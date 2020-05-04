package com.udacity.jdnd.course3.critter.user.db;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.schedule.db.ScheduleEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the form that customer request and response data takes. Maps
 * to the database directly.
 */

@Entity
@Table(name = "customer")
public class CustomerEntity implements Serializable {
    @Id
    @GeneratedValue
    private long id = -1;
    private String name;
    private String phoneNumber;
    private String notes;

    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, targetEntity = PetEntity.class, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private List<PetEntity> pets = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "employees", targetEntity = ScheduleEntity.class,
            fetch = FetchType.LAZY)
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<PetEntity> getPets() {
        return pets;
    }

    public void setPets(List<PetEntity> pets) {
        this.pets = pets;
    }
}
