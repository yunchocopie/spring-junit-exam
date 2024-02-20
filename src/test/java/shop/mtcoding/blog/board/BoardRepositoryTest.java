package shop.mtcoding.blog.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class) // 내가 만든 클래스는 import 해줘야 함
@DataJpaTest // DB 관련 객체들이 IoC에 뜬다.
public class BoardRepositoryTest {

    @Autowired // Test에서 DI하는 코드
    private BoardRepository boardRepository;

    @Test
    public void update_test() {
        // given
        int id = 1;
        String title = "안녕";

        // when
        boardRepository.update(id, title);

        // then (상태검사)
        List<Board> boardList = boardRepository.selectAll();
        Assertions.assertThat(boardList.getFirst().getTitle()).isEqualTo("안녕");
        Assertions.assertThat(boardList.getFirst().getContent()).isEqualTo("내용1");
        Assertions.assertThat(boardList.getFirst().getAuthor()).isEqualTo("홍길동");
        System.out.println(boardList);
    }

    @Test
    public void delete_test() {
        // given
        int id = 1;

        // when
        boardRepository.delete(id);

        // then (상태검사)
        List<Board> boardList = boardRepository.selectAll();
        Assertions.assertThat(boardList.getFirst().getId()).isEqualTo(2);
        System.out.println(boardList);
    }

    @Test
    public void selectAll_test() {
        // given

        // when
        List<Board> boardList = boardRepository.selectAll();

        // then (상태검사)
        // System.out.println(board);
        Assertions.assertThat(boardList.getFirst().getTitle()).isEqualTo("제목1");
        Assertions.assertThat(boardList.getFirst().getContent()).isEqualTo("내용1");
        Assertions.assertThat(boardList.getFirst().getAuthor()).isEqualTo("홍길동");
        Assertions.assertThat(boardList.size()).isEqualTo(8);

        System.out.println(boardList.getFirst());
    }

    @Test
    public void selectOne_test() {
        // given
        int id = 1;

        // when
        Board board = boardRepository.selectOne(id);

        // then (상태검사)
        // System.out.println(board);
        Assertions.assertThat(board.getTitle()).isEqualTo("제목1");
        Assertions.assertThat(board.getContent()).isEqualTo("내용1");
        Assertions.assertThat(board.getAuthor()).isEqualTo("홍길동");
    }

    @Test
    public void insert_test(){ // 테스트 메서드는 파라미터가 없다. 리턴도 없다.
        // given
        String title = "제목10";
        String content = "내용10";
        String author = "이순신";

        // when
        boardRepository.insert(title, content, author);

        // then -> 눈으로 확인 (쿼리)
    } // Rollback (자동)
}