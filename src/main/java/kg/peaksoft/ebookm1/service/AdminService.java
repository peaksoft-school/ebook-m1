package kg.peaksoft.ebookm1.service;

import kg.peaksoft.ebookm1.api.playLoads.dto.admin.AdminRequest;
import kg.peaksoft.ebookm1.api.playLoads.dto.admin.AdminResponse;
import kg.peaksoft.ebookm1.dataBase.entity.User;
import kg.peaksoft.ebookm1.dataBase.mapper.admin.AdminEditMapper;
import kg.peaksoft.ebookm1.dataBase.mapper.admin.AdminViewMapper;
import kg.peaksoft.ebookm1.dataBase.repository.UserRepository;
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

    public AdminResponse updateAdmin(Long id, AdminRequest request) {
        User admin = repository.findById(id).get();
        editMapper.updateAdmin(admin, request);
        return viewMapper.viewAdmin(repository.save(admin));
    }

    public List<AdminResponse> getAllAdmins() {
        return viewMapper.viewAdmins();
    }
}
