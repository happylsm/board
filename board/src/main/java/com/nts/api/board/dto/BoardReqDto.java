package com.nts.api.board.dto;

import com.nts.api.board.entity.BoardEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardReqDto {
    @Schema(description = "게시글 제목")
    private String title;

    @Schema(description = "게시글 내용(500자이내)")
    @Size(message = "게시글은 500자 이내로 작성해주세요.", max = 500)
    private String content;

    @Schema(description = "게시글 작성자")
    private String writer;

    public BoardEntity toEntity() {
        return BoardEntity.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .views(0)
                .deleteYn('N')
                .build();
    }
}
