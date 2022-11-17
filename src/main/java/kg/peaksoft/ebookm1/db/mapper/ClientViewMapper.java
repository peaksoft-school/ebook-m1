package kg.peaksoft.ebookm1.db.mapper;

import kg.peaksoft.ebookm1.api.payload.client.ClientResponse;
import kg.peaksoft.ebookm1.db.entity.Role;
import kg.peaksoft.ebookm1.db.entity.User;
import kg.peaksoft.ebookm1.db.repository.RoleRepository;
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
        response.setCreatedAt(user.getCreatedAt());
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
