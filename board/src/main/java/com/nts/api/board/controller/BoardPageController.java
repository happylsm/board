package com.nts.api.board.controller;


import com.nts.api.board.dto.BoardReqDto;
import com.nts.api.board.entity.BoardEntity;
import com.nts.api.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardPageController {
    private final BoardService boardService;

    @GetMapping("/write")
    public String write() {
        return "board/write";
    }


    @PostMapping("/writedo")
    public String writedo(BoardReqDto params, Model model) {
        try {
            boardService.save(params);
            model.addAttribute("message", "글 작성이 완료되었습니다.");
            model.addAttribute("searchUrl", "/board/list");
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("searchUrl", "/board/list");
        }

        return "board/message";
    }

    @GetMapping("/list")
    public String list(@org.jetbrains.annotations.NotNull Model model,
                       @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {Page<BoardEntity> list = null;

        list = boardService.pageList(pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "board/list";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") Long id) {
        model.addAttribute("board", boardService.findById(id));
        return "board/view";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                         Model model) {
        try {
            boardService.delete(id);
            model.addAttribute("message", "글 삭제가 완료되었습니다.");
            model.addAttribute("searchUrl", "/board/list");
        } catch (Exception e) {
            model.addAttribute("message", "글 삭제에 실패했습니다.");
            model.addAttribute("searchUrl", "/board/list");
        }

        return "board/message";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("board", boardService.findById(id));

        return "board/modify";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute("board") BoardReqDto board,
                         Model model) {
        try {
            boardService.update(id, board);
            model.addAttribute("message", "글 수정이 완료되었습니다.");
            model.addAttribute("searchUrl", "/board/list");
        } catch (Exception e) {
            model.addAttribute("message", "글 수정이 완료되었습니다.");
            model.addAttribute("searchUrl", "/board/list");
        }

        return "board/message";
    }
}
