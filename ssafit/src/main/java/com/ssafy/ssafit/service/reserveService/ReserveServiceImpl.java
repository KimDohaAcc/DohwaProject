package com.ssafy.ssafit.service.reserveService;

import com.ssafy.ssafit.domain.Reserve;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.repository.ReserveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReserveServiceImpl implements ReserveService {

    private final ReserveRepository reserveRepository;

    @Override
    public Reserve createReserve(Reserve reserve) {
        return reserveRepository.save(reserve);
    }

    @Override
    public boolean deleteReserve(Reserve reserve) {
        if (reserveRepository.existsById(reserve.getNum())) {
            reserveRepository.delete(reserve);
            return true;
        }
        return false;
    }

    @Override
    public void modifyReserve(Reserve reserve) {
        reserveRepository.save(reserve);
    }

    @Override
    public List<Reserve> getListByUser(User user) {
        return reserveRepository.findAllByUser(user);
    }
}
