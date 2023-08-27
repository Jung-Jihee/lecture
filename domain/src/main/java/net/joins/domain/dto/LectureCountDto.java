package net.joins.domain.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
public class LectureCountDto {

    Integer classNo;
    /*강연자*/
    String lecturer;

    /*강연시간*/
    String lecturetime;

    /*강의내용*/
    String content;
    /*강연장*/
    String classname;

    /*강의신청수*/
    Integer fcnt;

    @Builder
    public LectureCountDto(int classNo, String lecturer , String lecturetime, String content, String classname, int fcnt)
    {
        this.classNo = classNo;
        this.lecturer = lecturer;
        this.lecturetime = lecturetime;
        this.content = content;
        this.classname = classname;
        this.fcnt = fcnt;

    }

}
