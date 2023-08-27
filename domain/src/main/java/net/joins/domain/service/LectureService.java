package net.joins.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.joins.domain.dto.LectureCountDto;
import net.joins.domain.dto.LectureFormDto;
import net.joins.domain.dto.LectureRegDto;
import net.joins.domain.entity.Lecture;
import net.joins.domain.entity.LectureReg;
import net.joins.domain.entity.LectureRegVw;
import net.joins.domain.repository.LectureRegRepository;
import net.joins.domain.repository.LectureRegVwRepository;
import net.joins.domain.repository.LectureRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class LectureService {
    final LectureRepository lectureRepository;
    final LectureRegRepository lectureRegRepos;
    final LectureRegVwRepository lectureRegVwRepos;

    public List<Lecture> getLectureList(){ return lectureRepository.findAll();}

    public void getLectureSave(LectureFormDto lectureDto){
        String lectureTime = lectureDto.getLecturedate() + " " + lectureDto.getLecturehour() + ":" + lectureDto.getLectureminute();
        log.info("lectureTime:" + lectureTime);
        Lecture lecture = Lecture.builder()
                .lecturer(lectureDto.getLecturer())
                .classname(lectureDto.getClassname())
                .lecturetime(lectureTime)
                .content(lectureDto.getContent())
                .cnt(0)
                .build();
        lectureRepository.save(lecture);
    }
    public List<LectureReg> getLectureReg(int classNo){
        return lectureRegRepos.findByClassNo(classNo);
    }

    public List<Lecture> getViewLectureList(){
        return lectureRepository.findLectureList();
    }

    public LectureRegDto getLectureRegProc(LectureRegDto regDto){

        String nowDate ="";
        Date now = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        nowDate = dt.format(now);
        log.info("nowDate:"+ nowDate);
        Lecture lec = lectureRepository.findByClassNoAndLecturetimeIsGreaterThanEqual(regDto.getClassNo(),nowDate);
        LectureRegDto resultRegDto = new LectureRegDto();
        //해당 강의 강연시간 체크 : 신청가능여부 체크
        if(lec==null){
            resultRegDto.setResult("01");
            resultRegDto.setMsg("해당 강의는 신청불가능합니다.");
        }else{
            List<LectureReg> lreg = lectureRegRepos.findByClassNo(regDto.getClassNo());
            int lregCnt = lreg.size();
            LectureReg chkReg = lectureRegRepos.findByClassNoAndSabun(regDto.getClassNo(),regDto.getSabun());
            log.info(" lec.getCnt()-lregCnt : " + lec.getCnt() + ":"+lregCnt );
            if(lec.getCnt() <= lregCnt) {
                //신청인원, 신청자 체크
                resultRegDto.setResult("02");
                resultRegDto.setMsg("해당 강연은 수강신청이 마감되었습니다.");
            }
            else if(chkReg != null){
                //해당 강의 신청 여부 체크
                resultRegDto.setResult("03");
                resultRegDto.setMsg("이미 신청한 강연입니다.");
            }
            else{
                //신청가능
                resultRegDto.setLec(lec);
                this.getLectureRegSave(regDto);
                resultRegDto.setResult("00");
                resultRegDto.setMsg("강연신청이 성공했습니다.");
            }


        }

        return resultRegDto;
    }

    public void getLectureRegSave(LectureRegDto regDto){
        LectureReg reg = LectureReg.builder()
                .classNo(regDto.getClassNo())
                .sabun(regDto.getSabun())
                .build();
        lectureRegRepos.save(reg);

    }

    public LectureRegVw getLectureView(int classNo , String sabun){
        return lectureRegVwRepos.findByClassNoAndSabun(classNo, sabun);
    }

    public void getLectureDel(int seq){
        lectureRegRepos.deleteById(seq);
    }

    public List<LectureCountDto> getFavoriteLecture(){
        List<LectureCountDto> listcount = new ArrayList<>();
        List<Map<String,Object>> vw = lectureRepository.findFavoriteLectureList();
        for(Map<String,Object>  map : vw){
            LectureCountDto lecDto = LectureCountDto.builder()
                    .classname(map.get("class").toString())
                    .content(map.get("content").toString())
                    .classNo(Integer.parseInt(map.get("class_No").toString()))
                    .fcnt(Integer.parseInt(map.get("fcnt").toString()))
                    .lecturer(map.get("lecturer").toString())
                    .lecturetime(map.get("lecturetime").toString())
                    .build();
            listcount.add(lecDto);

        }
        return listcount;
    }
}
