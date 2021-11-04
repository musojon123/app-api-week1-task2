package uz.pdp.appapiweek1task2.entity;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Data
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String text;

    @OneToOne
    private Task task;

    @OneToOne
    private User user;

    @Column
    private Boolean isCorrect;
}
