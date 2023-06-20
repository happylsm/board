package com.nts.api.board.dto;

import com.nts.api.board.entity.BoardEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResDto {
    @Schema(description = "게시글 id")
    private Long id; // PK

    @Schema(description = "게시글 제목(500자 이내)")
    private String title;

    @Schema(description = "게시글 내용(500자 이내)")
    private String content;

    @Schema(description = "게시글 작성자(20자 이내)")
    private String writer;

    @Schema(description = "게시글 조회수")
    private int views;

    @Schema(description = "게시글 삭제여부(N|Y)")
    private char deleteYn;

    @Schema(description = "게시글 작성 일자(LocalDateTime)")
    private LocalDateTime createdDate;

    @Schema(description = "게시글 수정 일자(LocalDateTime)")
    private LocalDateTime modifiedDate;

    public BoardResDto(BoardEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getWriter();
        this.views = entity.getViews();
        this.deleteYn = entity.getDeleteYn();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
