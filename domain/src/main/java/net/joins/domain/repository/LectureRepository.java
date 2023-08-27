package net.joins.domain.repository;

import net.joins.domain.dto.LectureCountDto;
import net.joins.domain.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    List<Lecture> findAll();

    @Query(value = "SELECT * from tb_lecture " +
            " WHERE  timestampdiff(MINUTE ,now(),lecturetime) between -1440 and 10080;   ", nativeQuery = true)
    List<Lecture> findLectureList();

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    Lecture findByClassNoAndLecturetimeIsGreaterThanEqual(Integer class_No, String nowDate);

    @Query(value = "Select  a.class_No,lecturer, lecturetime, content, class ,b.fcnt " +
            "from tb_lecture a inner join " +
            "     (Select class_No, count(*) fcnt " +
            "      from tb_lecture_reg a " +
            "      where datediff(now(), created_Date) <= 3 " +
            "      group by class_No " +
            "     ) b on a.class_No = b.class_No " +
            "Order by B.fcnt Desc;   ", nativeQuery = true)
    List<Map<String,Object>> findFavoriteLectureList();
}
