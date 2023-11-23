package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Board;
import com.ssafy.ssafit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("SELECT b FROM Board b JOIN b.user u WHERE (:key = 'user' AND u.nickname LIKE %:word%) OR (:key = 'title' AND b.title LIKE %:word%) OR (:key = 'content' AND b.content LIKE %:word%)")
    List<Board> getListByCondition(@Param("key") String key, @Param("word") String word);
    List<Board> getBoardsByUser(User user);
}
