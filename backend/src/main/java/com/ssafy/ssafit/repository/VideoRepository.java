package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    @Query("SELECT v FROM Video v WHERE (:key = 'title' and v.title LIKE %:word%) or (:key = 'sort' and v.sort LIKE %:word%)")
    List<Video> getListByCondition(@Param("key") String key, @Param("word") String word);
}
