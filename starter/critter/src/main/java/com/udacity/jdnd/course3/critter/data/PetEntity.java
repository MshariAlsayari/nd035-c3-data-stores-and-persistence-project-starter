package com.udacity.jdnd.course3.critter.data;


import com.udacity.jdnd.course3.critter.pet.PetType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pet")
@Getter
@Setter
@NoArgsConstructor
public class PetEntity {
    @Id
    @GeneratedValue
    private int id;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private PetType type;

    @Column
    private String name;

    @Column
    private LocalDate birthDate;

    @Column
    private String notes;

    @ManyToOne
    private CustomerEntity customer;

    @ManyToOne
    private ScheduleEntity schedule;
}
