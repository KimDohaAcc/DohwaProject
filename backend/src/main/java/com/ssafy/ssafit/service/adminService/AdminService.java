package com.ssafy.ssafit.service.adminService;

import com.ssafy.ssafit.domain.Admin;
import com.ssafy.ssafit.domain.User;

import java.util.Optional;

public interface AdminService {
    Admin insertAdmin(Admin admin);

    Optional<Admin> findAdminByUser(User user);

    void removeAdmin(Admin admin);
}
