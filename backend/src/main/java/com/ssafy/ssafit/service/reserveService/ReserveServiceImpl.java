package com.ssafy.ssafit.service.reserveService;

import com.ssafy.ssafit.domain.Reserve;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.repository.ReserveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReserveServiceImpl implements ReserveService {

    private final ReserveRepository reserveRepository;

    @Override
    public Reserve createReserve(Reserve reserve) {
        return reserveRepository.save(reserve);
    }

    @Override
    public boolean deleteReserve(Long id) {
        if (reserveRepository.existsById(id)) {
            reserveRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void modifyReserve(Reserve reserve) {
        reserveRepository.save(reserve);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reserve> getListByUser(Optional<User> user) {
        return reserveRepository.findAllByUser(user);
    }
}