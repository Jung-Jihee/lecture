package net.joins.domain.repository;

import net.joins.domain.entity.LectureReg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LectureRegRepository extends JpaRepository<LectureReg, Integer> {
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    List<LectureReg> findByClassNo(Integer classNo);

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    LectureReg findByClassNoAndSabun(Integer classNo, String sabun);
}
