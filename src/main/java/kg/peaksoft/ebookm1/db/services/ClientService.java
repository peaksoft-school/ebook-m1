package kg.peaksoft.ebookm1.db.services;

import kg.peaksoft.ebookm1.api.controllers.payloads.dto.client.ClientRequest;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.client.ClientResponse;
import kg.peaksoft.ebookm1.db.entities.security.User;
import kg.peaksoft.ebookm1.db.mappers.client.ClientEditMapper;
import kg.peaksoft.ebookm1.db.mappers.client.ClientViewMapper;
import kg.peaksoft.ebookm1.db.repositories.UserRepository;
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

    public ClientResponse registration(ClientRequest request) {
        User user = editMapper.createUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.isActive();
        repository.save(user);
        log.info("The client has successfully registered: {}", user.getFirstName());
        return viewMapper.viewUser(user);
    }

    public ClientResponse update(ClientRequest request, Long id) {
        User user = repository.findById(id).get();
        editMapper.updateUser(user, request);
        log.info("The client has successfully updated his data: {}", user.getFirstName());
        return viewMapper.viewUser(repository.save(user));
    }

    public ClientResponse getById(Long id) {
        User client = repository.findById(id).get();
        log.info("Getting client by id: {}", client.getFirstName());
        return viewMapper.viewUser(client);
    }

    public List<ClientResponse> getAllClients() {
        log.info("Getting all clients: ");
        return viewMapper.viewClients();
    }

    public ClientResponse deleteById(Long id) {
        User user = repository.findById(id).get();
        repository.deleteById(id);
        log.info("Successfully deleted client by id: {}", user.getFirstName());
        return viewMapper.viewUser(user);
    }

    public ClientResponse getClientHistory(Long id) {
        User user = repository.findById(id).get();
        log.info("Getting a client by id: {}", user.getFirstName());
        return viewMapper.viewUser(user);
    }

    public ClientResponse deleteClientHistory(Long id) {
        User user = repository.findById(id).get();
        repository.deleteById(id);
        log.info("The client was successfully removed by ID from the database: {}", user.getFirstName());
        return viewMapper.viewUser(user);
    }
}
