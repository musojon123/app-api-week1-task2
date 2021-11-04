package uz.pdp.appapiweek1task2.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String text;

    @Column
    private String solution;

    @Column
    private String hint;

    @Column
    private String method;

    @Column
    private Boolean hasStar;

    @ManyToOne
    private ProgrammingLanguage language;
}
