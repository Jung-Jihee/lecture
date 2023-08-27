package net.joins.domain.dto;

import lombok.*;
import net.joins.domain.entity.Lecture;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
public class LectureRegDto {
    int classNo;
    String sabun;
    String result;
    String msg;
    Lecture lec;

    @Builder
    public LectureRegDto(int classNo, String sabun , String result, String msg,Lecture lec)
    {
        this.classNo = classNo;
        this.sabun = sabun;
        this.result = result;
        this.msg = msg;
        this.lec = lec;
    }

}
