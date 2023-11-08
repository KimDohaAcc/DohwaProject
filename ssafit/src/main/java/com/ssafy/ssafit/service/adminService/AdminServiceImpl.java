package com.ssafy.ssafit.service.adminService;

import com.ssafy.ssafit.domain.Admin;
import com.ssafy.ssafit.domain.User;
import com.ssafy.ssafit.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Transactional
    @Override
    public Admin insertAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Optional<Admin> findAdminByUser(User user) {
       return adminRepository.findByUser(user);
    }

    @Transactional
    @Override
    public void removeAdmin(Admin admin) {
        adminRepository.delete(admin);
    }
}
