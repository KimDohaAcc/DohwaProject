package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.domain.Reserve;
import com.ssafy.ssafit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReserveRepository  extends JpaRepository<Reserve, Long> {
    List<Reserve> findAllByUser(Optional<User> user);
}
