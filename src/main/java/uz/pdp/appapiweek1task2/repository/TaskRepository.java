package uz.pdp.appapiweek1task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appapiweek1task2.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
