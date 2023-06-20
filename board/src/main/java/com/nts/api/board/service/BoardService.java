package com.nts.api.board.service;

import com.nts.api.board.dto.BoardReqDto;
import com.nts.api.board.dto.BoardResDto;
import com.nts.api.board.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {

    /**
     * 게시글 생성
     */
    Long save(final BoardReqDto params);

    /**
     * 게시글 리스트 조회
     */
    List<BoardResDto> findAll();

    /**
     * 게시글 수정
     */
    Long update(final Long id, final BoardReqDto params);

    /**
     * 게시글 삭제
     */
    Long delete(final Long id);


    /**
     * 게시글 조회
     */
    BoardResDto findById(final Long id);

    Page<BoardEntity> pageList(Pageable pageable);
}
