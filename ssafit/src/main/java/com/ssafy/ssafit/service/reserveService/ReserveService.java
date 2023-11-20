package com.ssafy.ssafit.service.reserveService;

import com.ssafy.ssafit.domain.Reserve;
import com.ssafy.ssafit.domain.User;

import java.util.List;

public interface ReserveService {
    Reserve createReserve(Reserve reserve);

    boolean deleteReserve(Long id);

    void modifyReserve(Reserve reserve);

    List<Reserve> getListByUser(User user);


}
