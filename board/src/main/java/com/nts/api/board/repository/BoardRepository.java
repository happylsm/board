package com.nts.api.board.repository;

import com.nts.api.board.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @Query("SELECT b FROM BoardEntity b WHERE b.deleteYn = 'N'")
    Page<BoardEntity> findWithPagination(Pageable pageable);

}
