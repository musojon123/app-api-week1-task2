package uz.pdp.appapiweek1task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appapiweek1task2.entity.Example;

public interface ExampleRepository extends JpaRepository<Example, Integer> {
}
