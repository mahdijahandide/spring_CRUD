package ir.mhdi.portfolio.repository;

import ir.mhdi.portfolio.model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillsRepository extends JpaRepository<Skills ,Long> {
    //all crud database methods
}
