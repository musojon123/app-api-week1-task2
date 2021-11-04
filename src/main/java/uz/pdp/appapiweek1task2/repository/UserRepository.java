package uz.pdp.appapiweek1task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appapiweek1task2.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
}
