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
@Table(name = "tb_lecture_reg")
@Entity
@org.hibernate.annotations.DynamicUpdate
public class LectureReg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    Integer seq;

    @Column(name = "class_No" )
    Integer classNo;

    @Column(name = "sabun" ,length=5)
    String sabun;

    @Column(name = "created_Date", nullable = true , updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "modified_Date", nullable = true)
    @UpdateTimestamp
    private LocalDateTime modifiedDate;
}
