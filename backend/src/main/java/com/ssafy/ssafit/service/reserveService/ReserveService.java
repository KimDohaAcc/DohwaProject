package com.ssafy.ssafit.service.reserveService;

import com.ssafy.ssafit.domain.Reserve;
import com.ssafy.ssafit.domain.User;

import java.util.List;

public interface ReserveService {
    Reserve createReserve(Reserve reserve);

    boolean deleteReserve(Reserve reserve);

    void modifyReserve(Reserve reserve);

    List<Reserve> getListByUser(User user);
}
