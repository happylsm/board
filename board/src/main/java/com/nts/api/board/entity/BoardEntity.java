package com.nts.api.board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
@Entity
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String writer;
    private char deleteYn;
    private int views;
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime modifiedDate;

    @Builder
    public BoardEntity(String title, String content, int views, String writer, char deleteYn) {
        this.title = title;
        this.content = content;
        this.views = views;
        this.writer = writer;
        this.deleteYn = deleteYn;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.modifiedDate = LocalDateTime.now();
    }

    public void addViews() {
        this.views++;
    }

    public void delete() {
        this.deleteYn = 'Y';
    }
}
