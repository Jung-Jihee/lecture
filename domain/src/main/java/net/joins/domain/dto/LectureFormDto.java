package net.joins.domain.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
public class LectureFormDto {
    /*강연자*/
    String lecturer;
    /*강연장*/
    String classname;
    /*강의날짜*/
    String lecturedate;
    /*강의시간 - 시간 */
    String lecturehour;
    /*강의시간 - 분 */
    String lectureminute;
    /*강의내용*/
    String content;


    @Builder
    public LectureFormDto(String lecturer, String classname,String lecturedate,String lecturehour,String lectureminute,String content){
        this.lecturer = lecturer;
        this.classname = classname;
        this.lecturedate = lecturedate;
        this.lecturehour = lecturehour;
        this.lectureminute = lectureminute;
        this.content = content;

    }

}
