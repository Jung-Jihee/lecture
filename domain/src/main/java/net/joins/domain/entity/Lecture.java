package net.joins.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_lecture")
@Entity
@org.hibernate.annotations.DynamicUpdate
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "created_Date", nullable = true , updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "modified_Date", nullable = true)
    @UpdateTimestamp
    private LocalDateTime modifiedDate;

}
