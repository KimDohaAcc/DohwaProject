package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Board;
import com.ssafy.ssafit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("SELECT b FROM Board b WHERE :key LIKE %:word% ORDER BY :orderBy + :orderByDir")
    List<Board> getListByCondition(@Param("key") String key, @Param("word") String word, @Param("orderBy") String orderBy, @Param("orderByDir") String orderByDir);

    List<Board> getBoardsByUser(User user);
}
