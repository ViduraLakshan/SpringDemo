package com.example.demo.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String email;

    private LocalDate dob;
    @Transient
    private Integer age;


    public Integer getAge() {
        return Period.between(this.dob,LocalDate.now()).getYears();
    }
}
