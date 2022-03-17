package com.udacity.jdnd.course3.critter.schedule;


import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
public class ScheduleEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private LocalDate date;

    @Column
    @ElementCollection
    private List<EmployeeSkill> skills = new ArrayList<>();

    @OneToMany(mappedBy = "schedulePet", cascade = CascadeType.ALL)
    private List<PetEntity> petEntityList;

    @OneToMany(mappedBy = "scheduleEmployee", cascade = CascadeType.ALL)
    private List<EmployeeEntity> employeeEntityList;
}
