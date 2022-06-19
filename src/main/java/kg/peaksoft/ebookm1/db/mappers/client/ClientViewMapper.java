package kg.peaksoft.ebookm1.db.mappers.client;

import kg.peaksoft.ebookm1.api.controllers.payloads.dto.client.ClientResponse;
import kg.peaksoft.ebookm1.db.entities.security.Role;
import kg.peaksoft.ebookm1.db.entities.security.User;
import kg.peaksoft.ebookm1.db.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientViewMapper {

    private final RoleRepository roleRepository;

    public ClientResponse viewUser(User user) {
        if (user == null) {
            return null;
        }
        ClientResponse response = new ClientResponse();
        if (user.getId() != null) {
            response.setId(Long.valueOf(user.getId()));
        }
        response.setFirstName(user.getFirstName());
        response.setEmail(user.getEmail());
        response.setCreated(user.getCreated());
        response.setOperationList(user.getHistoryOperation());
        response.setActive(true);
        return response;
    }

    public List<ClientResponse> viewClients() {
        List<ClientResponse> clients = new ArrayList<>();
        Role role = roleRepository.findById(3L).get();
        for (User client : role.getUsers()) {
            clients.add(viewUser(client));
        }
        return clients;
    }
}
