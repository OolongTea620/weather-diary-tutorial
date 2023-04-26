package zerobase.weather.controller;

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
    @PostMapping("/create/diary")
    void createDiary(
        @RequestParam @DateTimeFormat(iso= ISO.DATE) LocalDate date,
        @RequestBody String text) {
        diaryService.createDiary(date, text);
    }

    @GetMapping("/read/diary")
    List<Diary> readDairy(
        @RequestParam @DateTimeFormat(iso= ISO.DATE) LocalDate date
    ) {
        return diaryService.readDiary(date);
    }

    @GetMapping("/read/diaries")
    List<Diary> readDiaries(
        @RequestParam @DateTimeFormat(iso= ISO.DATE) LocalDate startDate,
        @RequestParam @DateTimeFormat(iso= ISO.DATE) LocalDate endDate
    ) {
        return diaryService.readDiaries(startDate,endDate);
    }

    @PutMapping("/update/diary")
    void updateDairy(
        @RequestParam @DateTimeFormat(iso= ISO.DATE) LocalDate date,
        @RequestBody String text
    ) {
        diaryService.updateDiary(date,text);
    }

    @DeleteMapping("/delete/diary")
    void deleteDairy(
        @RequestParam @DateTimeFormat(iso= ISO.DATE) LocalDate date
    ) {
        diaryService.deleteDiary(date);
    }
}
