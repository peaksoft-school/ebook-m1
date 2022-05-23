package kg.peaksoft.ebookm1.service;

import kg.peaksoft.ebookm1.dto.user.UserEditMapper;
import kg.peaksoft.ebookm1.dto.user.UserRequest;
import kg.peaksoft.ebookm1.dto.user.UserResponse;
import kg.peaksoft.ebookm1.dto.user.UserViewMapper;
import kg.peaksoft.ebookm1.dto.user.UserEditMapper;
import kg.peaksoft.ebookm1.dto.user.UserRequest;
import kg.peaksoft.ebookm1.dto.user.UserResponse;
import kg.peaksoft.ebookm1.dto.user.UserViewMapper;
import kg.peaksoft.ebookm1.entity.User;
import kg.peaksoft.ebookm1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserEditMapper editMapper;
    private final UserViewMapper viewMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("user with email not found"));
    }

    public UserResponse create(UserRequest request) {
        User user = editMapper.createUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.isActive();
        repository.save(user);
        return viewMapper.viewUser(user);
    }

    public UserResponse update(UserRequest request, Long id) {
        User user = repository.findById(id).get();
        editMapper.updateUser(user, request);
        return viewMapper.viewUser(repository.save(user));
    }

    public UserResponse getById(Long id) {
        User user = repository.findById(id).get();
        return viewMapper.viewUser(user);
    }

    public List<UserResponse> getAllUsers() {
        return viewMapper.viewUsers(repository.findAll());
    }

    public UserResponse deleteById(Long id) {
        User user = repository.findById(id).get();
        repository.deleteById(id);
        return viewMapper.viewUser(user);
    }
}

