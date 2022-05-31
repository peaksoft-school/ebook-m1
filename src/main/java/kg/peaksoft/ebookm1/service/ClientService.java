package kg.peaksoft.ebookm1.service;

import kg.peaksoft.ebookm1.dataBase.mapper.client.ClientEditMapper;
import kg.peaksoft.ebookm1.api.playLoads.dto.client.ClientRequest;
import kg.peaksoft.ebookm1.api.playLoads.dto.client.ClientResponse;
import kg.peaksoft.ebookm1.dataBase.mapper.client.ClientViewMapper;
import kg.peaksoft.ebookm1.dataBase.entity.User;
import kg.peaksoft.ebookm1.dataBase.repository.UserRepository;
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
public class ClientService implements UserDetailsService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ClientEditMapper editMapper;
    private final ClientViewMapper viewMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("user with email not found"));
    }

    public ClientResponse createClient(ClientRequest request) {
        User user = editMapper.createUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.isActive();
        repository.save(user);
        return viewMapper.viewUser(user);
    }

    public ClientResponse update(ClientRequest request, Long id) {
        User user = repository.findById(id).get();
        editMapper.updateUser(user, request);
        return viewMapper.viewUser(repository.save(user));
    }

    public ClientResponse getById(Long id) {
        User user = repository.findById(id).get();
        return viewMapper.viewUser(user);
    }

    public List<ClientResponse> getAllClients() {
        return viewMapper.viewClients();
    }

    public ClientResponse deleteById(Long id) {
        User user = repository.findById(id).get();
        repository.deleteById(id);
        return viewMapper.viewUser(user);
    }
}

