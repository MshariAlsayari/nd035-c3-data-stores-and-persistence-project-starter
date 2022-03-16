package com.udacity.jdnd.course3.critter.schedule;


import com.udacity.jdnd.course3.critter.user.EmployeeEntity;
import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private LocalDateTime date;

    @Column
    @ElementCollection
    private List<EmployeeSkill> skills = new ArrayList<>();

    @OneToMany(mappedBy = "schedule")
    private List<PetEntity> petEntityList;

    @OneToMany(mappedBy = "schedule")
    private List<EmployeeEntity> employeeEntityList;
}
