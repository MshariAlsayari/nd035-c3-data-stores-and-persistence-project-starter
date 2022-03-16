package com.udacity.jdnd.course3.critter.data;


import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column
    @Nationalized
    private String name;

    @Column
    @ElementCollection
    private List<EmployeeSkill> skills = new ArrayList<>();

    @Column
    @ElementCollection
    private List<DayOfWeek> days = new ArrayList<>();

    @ManyToOne
    private ScheduleEntity schedule;


}
