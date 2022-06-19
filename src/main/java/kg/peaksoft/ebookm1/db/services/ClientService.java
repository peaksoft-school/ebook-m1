package kg.peaksoft.ebookm1.db.services;

import kg.peaksoft.ebookm1.api.controllers.payloads.dto.client.ClientRequest;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.client.ClientResponse;
import kg.peaksoft.ebookm1.db.entities.security.User;
import kg.peaksoft.ebookm1.db.mappers.client.ClientEditMapper;
import kg.peaksoft.ebookm1.db.mappers.client.ClientViewMapper;
import kg.peaksoft.ebookm1.db.repositories.UserRepository;
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

    public ClientResponse create(ClientRequest request) {
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
        User client = repository.findById(id).get();
        return viewMapper.viewUser(client);
    }

    public List<ClientResponse> getAllClients() {
        return viewMapper.viewClients();
    }

    public ClientResponse deleteById(Long id) {
        User user = repository.findById(id).get();
        repository.deleteById(id);
        return viewMapper.viewUser(user);
    }

    public ClientResponse getClientHistory(Long id) {
        User user = repository.findById(id).get();
        return viewMapper.viewUser(user);
    }

    public ClientResponse deleteClientHistory(Long id) {
        User user = repository.findById(id).get();
        repository.deleteById(id);
        return viewMapper.viewUser(user);
    }
}
