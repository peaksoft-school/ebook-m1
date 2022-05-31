package kg.peaksoft.ebookm1.service;

import kg.peaksoft.ebookm1.dto.admin.AdminRequest;
import kg.peaksoft.ebookm1.dto.admin.AdminResponse;
import kg.peaksoft.ebookm1.entity.User;
import kg.peaksoft.ebookm1.mapper.admin.AdminEditMapper;
import kg.peaksoft.ebookm1.mapper.admin.AdminViewMapper;
import kg.peaksoft.ebookm1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final AdminEditMapper editMapper;
    private final AdminViewMapper viewMapper;

    public AdminResponse createAdmin(AdminRequest request) {
        User admin = editMapper.createAdmin(request);
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.isActive();
        repository.save(admin);
        return viewMapper.viewAdmin(admin);
    }

    public List<AdminResponse> getAllAdmins() {
        return viewMapper.viewAdmins();
    }
}
