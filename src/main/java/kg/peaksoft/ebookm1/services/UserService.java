package kg.peaksoft.ebookm1.services;

import kg.peaksoft.ebookm1.dataBase.mappers.user.UserEditMapper;
import kg.peaksoft.ebookm1.api.payloads.dto.user.UserRequest;
import kg.peaksoft.ebookm1.api.payloads.dto.user.UserResponse;
import kg.peaksoft.ebookm1.dataBase.mappers.user.UserViewMapper;
import kg.peaksoft.ebookm1.dataBase.entities.security.User;
import kg.peaksoft.ebookm1.dataBase.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
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
        log.error("User with email not found: ");
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("user with email not found"));
    }

    public UserResponse create(UserRequest request) {
        User user = editMapper.createUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.isActive();
        repository.save(user);
        log.info("The client has successfully registered: {}", user.getFirstName());
        return viewMapper.viewUser(user);
    }

    public UserResponse update(UserRequest request, Long id) {
        User user = repository.findById(id).get();
        editMapper.updateUser(user, request);
        log.info("The client has successfully updated his data: {}", user.getFirstName());
        return viewMapper.viewUser(repository.save(user));
    }

    public UserResponse getById(Long id) {
        User user = repository.findById(id).get();
        log.info("Getting a client by id: {}", user.getFirstName());
        return viewMapper.viewUser(user);
    }

    public List<UserResponse> getAllUsers() {
        log.info("Getting all clients: ");
        return viewMapper.viewUsers(repository.findAll());
    }

    public UserResponse deleteById(Long id) {
        User user = repository.findById(id).get();
        repository.deleteById(id);
        log.info("The client was successfully removed by ID from the database: {}", user.getFirstName());
        return viewMapper.viewUser(user);
    }
}
