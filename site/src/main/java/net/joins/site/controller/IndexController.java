package net.joins.site.controller;

import net.joins.domain.dto.LectureCountDto;
import net.joins.domain.dto.LectureFormDto;
import net.joins.domain.dto.LectureRegDto;
import net.joins.domain.entity.Lecture;
import net.joins.domain.entity.LectureReg;
import net.joins.domain.entity.LectureRegVw;
import net.joins.domain.repository.LectureRepository;
import net.joins.domain.service.LectureService;
import net.joins.webapp.vo.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class IndexController {
    final LectureService lectureService;

    @GetMapping
    public String index(Model model, String msg) {
        model.addAttribute("msg", msg);

        return "index";
    }
    /* 전체 강연 목록 */
    @GetMapping("list")
    public ResponseEntity<Object> lectureList() {
        List<Lecture> list = lectureService.getLectureList();
        return ApiResponse.of().add("list", list).toResponseEntity();
    }
    /*강연등록*/
    @PostMapping("lecturesave")
    public ResponseEntity<Object> getSaveProc(@RequestBody LectureFormDto lectureDto) {
        lectureService.getLectureSave(lectureDto);
        return ApiResponse.of().add("result","OK").toResponseEntity();
    }
    /*강연신청자 목록(강연별 신청한 사번 목록)*/
    @GetMapping("classreg")
    public  ResponseEntity<Object> getClassReg(int classNo){
        List<LectureReg> classList = lectureService.getLectureReg(classNo);
        return ApiResponse.of().add("classlist", classList).toResponseEntity();
    }
    /*Front 강연 목록(신청 가능한 싯점부터 강연시작시간 1일 후까지 노출)*/
    @GetMapping("front/list")
    public ResponseEntity<Object> frontLectureList() {
        List<Lecture> list = lectureService.getViewLectureList();
        return ApiResponse.of().add("list", list).toResponseEntity();
    }
    /*Front 강연 신청(사번 입력, 같은 강연 중복 신청 제한)*/
    @PostMapping("front/lectureproc")
    public ResponseEntity<Object> getSaveProc(@RequestBody LectureRegDto reg) {
        LectureRegDto result = lectureService.getLectureRegProc(reg);

        return ApiResponse.of().add("result", result).toResponseEntity();
    }

    /*신청내역 조회(사번 입력)*/
    @PostMapping("front/lectureView")
    public  ResponseEntity<Object> getLectureView(int classNo,String sabun){
        LectureRegVw vw = lectureService.getLectureView(classNo,sabun);
        return ApiResponse.of().add("result", vw).toResponseEntity();
    }

    /*신청한 강연 취소*/
    @GetMapping("front/lectureDel")
    public  ResponseEntity<Object> getLectureDelete(int seq) {
        lectureService.getLectureDel(seq);
        return ApiResponse.of().add("result","OK").toResponseEntity();
    }
    /*실시간 인기 강연*/
    @GetMapping("front/favorite")
    public ResponseEntity<Object> getFavoriteLecture(){
        List<LectureCountDto> result = lectureService.getFavoriteLecture();
        return ApiResponse.of().add("result", result).toResponseEntity();
    }
}

