package zerobase.weather.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import zerobase.weather.domain.Diary;
import zerobase.weather.repository.DiaryRepository;

@Service
public class DiaryService {

    @Value("${openweathermap.key}")
    private String apiKey;

    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public void createDiary(LocalDate date, String text) {
        // Open weather Map 에서 데이터 받아오기
        String weatherData = getWeatherString();
        // 받아온 날씨 데이터 파싱하기
        System.out.println(weatherData);
        Map<String,Object> parsedWeather = parseWeather(weatherData);
        // 우리 db에 저장하기
        Diary nowDiary = new Diary();
        nowDiary.setWeather(parsedWeather.get("main").toString());
        nowDiary.setIcon(parsedWeather.get("icon").toString());
        nowDiary.setTemperature((double) parsedWeather.get("temp"));
        nowDiary.setText(text);
        nowDiary.setDate(date);
        diaryRepository.save(nowDiary);
    }

    private String getWeatherString() {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=" + apiKey;
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }else{
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = br.readLine()) != null){
                response.append(inputLine);
            }
            br.close();
            return  response.toString();
        } catch (Exception e) {
            return "failed to get response";
        }
    }

    /**
     * String을 받아서 json 형식으로 파싱하는 역할
     * @param jsonString
     */
    private Map<String, Object> parseWeather(String jsonString) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) jsonParser.parse(jsonString);
        }catch (ParseException e) {
            throw new RuntimeException(e);
        }
        JSONObject mainData = (JSONObject) jsonObject.get("main");
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("temp", mainData.get("temp"));
        JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
        JSONObject weatherData = (JSONObject) weatherArray.get(0);
        resultMap.put("main", weatherData.get("main"));
        resultMap.put("icon", weatherData.get("icon"));
        return resultMap;
    }

    public List<Diary> readDiary(LocalDate date) {
        return diaryRepository.findAllByDate(date);
    }
}
