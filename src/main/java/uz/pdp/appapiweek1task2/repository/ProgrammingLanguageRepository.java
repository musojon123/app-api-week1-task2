package uz.pdp.appapiweek1task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appapiweek1task2.entity.ProgrammingLanguage;

public interface ProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage, Integer> {
    boolean existsByNameAndIdNot(String name, Integer id);
    boolean existsByName(String name);
}
