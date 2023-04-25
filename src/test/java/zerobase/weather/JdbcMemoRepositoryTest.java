package zerobase.weather;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;
import zerobase.weather.repository.JdbcMemoRepository;

@SpringBootTest
@Transactional // 아무리 테스트 뒤에도 데이터베이스 상태가 원 상태로 복구가 된다.
public class JdbcMemoRepositoryTest {
    @Autowired
    JdbcMemoRepository jdbcMemoRepository;

    @Test
    void insertMemoTest() {
        //given
        Memo newMemo = new Memo(2, "2 this is new memo");

        //when
        jdbcMemoRepository.save(newMemo);

        //then
        Optional<Memo> result =  jdbcMemoRepository.findById(2);
        assertEquals(result.get().getText(), "2 this is new memo");
    }

    @Test
    void findAllMemoTest() {
        //given
        List<Memo> memoList = jdbcMemoRepository.findAll();
        System.out.println(memoList.toString());
        assertNotNull(memoList);
    }

}
