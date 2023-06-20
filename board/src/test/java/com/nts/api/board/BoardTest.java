package com.nts.api.board;

import com.nts.api.board.entity.BoardEntity;
import com.nts.api.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardTest {
    @Autowired
    BoardRepository boardRepository;

    @Test
    void save() {
        BoardEntity params = BoardEntity.builder()
                .title("제목")
                .content("내용")
                .writer("승승이")
                .deleteYn('N')
                .build();

        boardRepository.save(params);

        BoardEntity entity = boardRepository.findById((long) 1).get();
        assertThat(entity.getTitle()).isEqualTo("제목");
        assertThat(entity.getContent()).isEqualTo("내용");
        assertThat(entity.getWriter()).isEqualTo("승승이");
    }

    @Test
    void findAll() {
        long boardsCount = boardRepository.count();
        List<BoardEntity> boards = boardRepository.findAll();
    }

    @Test
    void delete() {
        BoardEntity entity = boardRepository.findById((long) 1).get();
        boardRepository.delete(entity);
    }
}
