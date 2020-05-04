package com.udacity.jdnd.course3.critter.user.db;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.schedule.db.ScheduleEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the form that customer request and response data takes. Maps
 * to the database directly.
 */

@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String phoneNumber;
    private String notes;

    @OneToMany(cascade = {CascadeType.ALL}, targetEntity = PetEntity.class, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private List<PetEntity> pets = new ArrayList<>();

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
