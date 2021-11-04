package uz.pdp.appapiweek1task2.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Example {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String text;

    @OneToOne
    private Task task;
}
