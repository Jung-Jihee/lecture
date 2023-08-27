package net.joins.domain.repository;


import net.joins.domain.entity.LectureRegVw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public interface LectureRegVwRepository  extends JpaRepository<LectureRegVw, Integer>{

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    LectureRegVw findByClassNoAndSabun(Integer classNo, String sabun);
}
