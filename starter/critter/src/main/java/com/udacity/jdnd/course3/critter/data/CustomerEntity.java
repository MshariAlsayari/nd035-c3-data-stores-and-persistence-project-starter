package com.udacity.jdnd.course3.critter.data;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column
    @Nationalized
    private String name;

    @Column
    private String phoneNumber;

    @Column
    private String notes;

    @OneToMany(mappedBy = "customer")
    private List<PetEntity> petEntityList;
}
