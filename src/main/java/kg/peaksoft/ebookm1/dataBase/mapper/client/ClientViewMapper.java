package kg.peaksoft.ebookm1.dataBase.mapper.client;

import kg.peaksoft.ebookm1.api.playLoads.dto.client.ClientResponse;
import kg.peaksoft.ebookm1.dataBase.entity.Role;
import kg.peaksoft.ebookm1.dataBase.entity.User;
import kg.peaksoft.ebookm1.dataBase.repository.RoleRepository;
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
        response.setActive(true);
        return response;
    }

    public List<ClientResponse> viewClients() {
        List<ClientResponse> clientUsers = new ArrayList<>();
        Role role = roleRepository.findById(3L).get();
        for (User client : role.getUsers()) {
            clientUsers.add(viewUser(client));
        }
        return clientUsers;
    }
}
