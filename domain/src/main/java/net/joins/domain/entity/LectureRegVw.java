package net.joins.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "view_lecturereg")
@Entity
public class LectureRegVw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    Integer seq;

    @Column(name = "class_No")
    Integer classNo;

    @Column(name = "lecturer" ,length=20)
    String lecturer;

    @Column(name = "class" ,length=20)
    String classname;

    @Column(name = "lecturetime" ,length=50)
    String lecturetime;

    @Column(name = "cnt" )
    Integer cnt;

    @Column(name = "content" ,length=500)
    String content;

    @Column(name = "sabun" ,length=5)
    String sabun;

    @Column(name = "created_Date", nullable = true)
    private LocalDateTime createdDate;

    @Column(name = "modified_Date", nullable = true)
    private LocalDateTime modifiedDate;
}
