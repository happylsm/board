package com.nts.api.board.service.impl;

import com.nts.api.board.dto.BoardReqDto;
import com.nts.api.board.dto.BoardResDto;
import com.nts.api.board.entity.BoardEntity;
import com.nts.api.board.repository.BoardRepository;
import com.nts.api.board.service.BoardService;
import com.nts.api.exception.CustomException;
import com.nts.api.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    @Autowired
    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public Long save(BoardReqDto params) {
        BoardEntity entity = boardRepository.save(params.toEntity());
        return entity.getId();
    }

    @Override
    public List<BoardResDto> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id", "createdDate");
        List<BoardEntity> list = boardRepository.findAll(sort);
        return list.stream().map(BoardResDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long update(Long id, BoardReqDto params) {
        BoardEntity entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(params.getTitle(), params.getContent());
        return id;
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        BoardEntity entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.delete();
        return id;
    }

    @Override
    @Transactional
    public BoardResDto findById(final Long id) {
        BoardEntity entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.addViews();
        return new BoardResDto(entity);
    }

    @Override
    public Page<BoardEntity> pageList(Pageable pageable) {
        return boardRepository.findWithPagination(pageable);
    }
}
