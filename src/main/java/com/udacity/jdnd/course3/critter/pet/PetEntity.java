package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.schedule.db.ScheduleEntity;
import com.udacity.jdnd.course3.critter.user.db.CustomerEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Represents the form that pet request and response data takes.  Maps
 * to the database directly.
 */
@Entity
@Table(name = "pet")
public class PetEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id = -1;
    private PetType type;
    private String name;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, targetEntity = CustomerEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private CustomerEntity customer;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "pets", targetEntity = ScheduleEntity.class, fetch = FetchType.LAZY)
    private List<ScheduleEntity> schedules;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetEntity )) return false;
        return id != -1 && id == ((PetEntity) o).getId();
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
