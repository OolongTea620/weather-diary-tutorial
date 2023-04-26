package zerobase.weather.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    /**
     * 날씨 일기 생성 API
     * @param date
     * @param text
     */
    @ApiOperation(value="일기 텍스트와 날씨를 이용해서 DB에 일기 저장", notes = "이것은 노트")
    @PostMapping("/create/diary")
    void createDiary(
        @RequestParam @DateTimeFormat(iso= ISO.DATE) @ApiParam(value = "쓰고 싶은 날짜 : yyyy-MM-dd", example = "2022-04-02") LocalDate date,
        @RequestBody @ApiParam(value = "쓰고 싶은 내용", example = "여기에 내용") String text) {
        diaryService.createDiary(date, text);
    }

    @ApiOperation(value="선택한 날짜의 모든 일기 데이터를 가져옵니다")
    @GetMapping("/read/diary")
    List<Diary> readDairy(
        @RequestParam @DateTimeFormat(iso= ISO.DATE) @ApiParam(value = "조회날 : yyyy-MM-dd", example = "2022-04-02") LocalDate date
    ) {
        return diaryService.readDiary(date);
    }

    @ApiOperation(value="선택한 기간중의 모든 일기 데이터를 가져옵니다")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(
        @RequestParam @DateTimeFormat(iso= ISO.DATE) @ApiParam(value = "조회 시작 날(포함됨) : yyyy-MM-dd", example = "2022-04-02") LocalDate startDate,
        @RequestParam @DateTimeFormat(iso= ISO.DATE) @ApiParam(value = "조회한 기간의 마지막 날 (포함됨) : yyyy-MM-dd") LocalDate endDate
    ) {
        return diaryService.readDiaries(startDate,endDate);
    }

    @ApiOperation(value="선택한 기간중의 모든 일기 내용을 수정합니다")
    @PutMapping("/update/diary")
    void updateDairy(
        @RequestParam @DateTimeFormat(iso= ISO.DATE) @ApiParam(value = "수정하고 싶은 일기 날짜 : yyyy-MM-dd", example = "2022-04-02") LocalDate date,
        @RequestBody @ApiParam(value = "수정 내용") String text
    ) {
        diaryService.updateDiary(date,text);
    }

    @ApiOperation(value="지정한 기간의 일기 데이터를 삭제합니다")
    @DeleteMapping("/delete/diary")
    void deleteDairy(
        @RequestParam @DateTimeFormat(iso= ISO.DATE) @ApiParam(value = "삭제하고 싶은 날짜 : yyyy-MM-dd", example = "2022-04-02") LocalDate date
    ) {
        diaryService.deleteDiary(date);
    }
}
