package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Reserve;
import com.ssafy.ssafit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ReserveRepository  extends JpaRepository<Reserve, Long> {
    List<Reserve> findAllByUser(Optional<User> user);
}
