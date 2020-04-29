package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.db.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.db.EmployeeEntity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Represents the form that pet request and response data takes.  Maps
 * to the database directly.
 */
@Entity
@Table(name = "pet")
public class PetEntity {
    @Id
    @GeneratedValue
    private long id;
    private PetType type;
    private String name;

    @ManyToOne(cascade = CascadeType.REFRESH, targetEntity = CustomerEntity.class)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    private LocalDate birthDate;
    private String notes;

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
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
}
