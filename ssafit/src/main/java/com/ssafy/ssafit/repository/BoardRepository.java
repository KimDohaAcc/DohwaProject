package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select c from Board c where id=:id")
    Board asdf(@Param("id")Long id);
}
