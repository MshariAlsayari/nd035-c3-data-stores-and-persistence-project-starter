package com.udacity.jdnd.course3.critter.user;


import com.udacity.jdnd.course3.critter.pet.PetEntity;
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
    private Long id;

    @Column
    @Nationalized
    private String name;

    @Column
    private String phoneNumber;

    @Column
    private String notes;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<PetEntity> petEntityList;
}
