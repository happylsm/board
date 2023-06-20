package com.nts.api.board.controller;

import com.nts.api.board.dto.BoardReqDto;
import com.nts.api.board.service.BoardService;
import com.nts.api.common.BaseResDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    @ApiOperation(value = "게시글 저장", notes = "게시글을 저장하는 api")
    @PostMapping("/save")
    public BaseResDto<?> save(@RequestBody @Validated final BoardReqDto params) {
        return new BaseResDto<>(HttpStatus.OK.value(), "게시물 작성을 완료했습니다", boardService.save(params));
    }

    @ApiOperation(value = "전체 게시글 리스트", notes = "전체 게시글을 조회하는 api")
    @GetMapping("/allPost")
    public BaseResDto<?> findAll() {
        return new BaseResDto<>(HttpStatus.OK.value(), "전체 게시글 조회를 완료했습니다", boardService.findAll());
    }

    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정하는 api")
    @PatchMapping("/update/{id}")
    public BaseResDto<?> save(@PathVariable final Long id, @RequestBody @Validated final BoardReqDto params) {
        return new BaseResDto<>(HttpStatus.OK.value(), "게시글 수정을 완료했습니다", boardService.update(id, params));
    }

    @ApiOperation(value = "게시글 조회", notes = "게시글 상세정보를 확인하는 api")
    @GetMapping("/post/{id}")
    public BaseResDto<?> findById(@PathVariable final Long id) {
        return new BaseResDto<>(HttpStatus.OK.value(), "게시글 조회를 완료했습니다", boardService.findById(id));
    }

    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제하는 api")
    @DeleteMapping("/delete/{id}")
    public BaseResDto<?> delete(@PathVariable final Long id) {
        return new BaseResDto<>(HttpStatus.OK.value(), "게시글 삭제를 완료했습니다", boardService.delete(id));
    }

}
