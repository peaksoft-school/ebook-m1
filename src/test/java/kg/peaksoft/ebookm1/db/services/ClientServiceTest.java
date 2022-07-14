package kg.peaksoft.ebookm1.db.services;

import kg.peaksoft.ebookm1.api.controllers.payloads.dto.client.ClientRequest;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.client.ClientResponse;
import kg.peaksoft.ebookm1.db.entities.security.User;
import kg.peaksoft.ebookm1.db.mappers.client.ClientEditMapper;
import kg.peaksoft.ebookm1.db.mappers.client.ClientViewMapper;
import kg.peaksoft.ebookm1.db.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    private UserRepository repository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @Mock
    private ClientEditMapper editMapper;
    @Mock
    private ClientViewMapper viewMapper;
    @InjectMocks
    private ClientService clientService;

    private ClientRequest clientRequest;
    private ClientResponse clientResponse;
    private User client;

    @BeforeEach
    void prepare() {
        clientRequest = ClientRequest.builder()
                .email("client@test.com")
                .firstName("clientName")
                .password("password123")
                .build();

        clientResponse = ClientResponse.builder()
                .id(5L)
                .email("client@test.com")
                .firstName("clientName")
                .build();

        client = User.builder()
                .id(5L)
                .email("client@test.com")
                .firstName("clientName")
                .password("password123")
                .build();


    }

    @Test
    @DisplayName("Test for get User by email(username)")
    void loadUserByUsername() {
        String email = "client@test.com";
        doReturn(Optional.of(client)).when(repository).findByEmail(email);
        assertThat(clientService.loadUserByUsername(email)).isEqualTo(client);
    }

    @Test
    @DisplayName("Test for throw exception if email is not expected")
    void throwExceptionIfUserEmailNotExpected() {
        String message = "user with email not found";
        var exception = assertThrows(UsernameNotFoundException.class,
                () -> clientService.loadUserByUsername(null));
        Assertions.assertThat(exception.getMessage()).isEqualTo(message);
    }

    @Test
    @DisplayName("Test for registration")
    void registration() {
        doReturn(client).when(editMapper).createUser(clientRequest);
        String password = "password123";
        doReturn(password).when(passwordEncoder).encode(clientRequest.getPassword());
        client.setActive(true);
        doReturn(client).when(repository).save(client);
        doReturn(clientResponse).when(viewMapper).viewUser(client);

        assertThat(clientService.registration(clientRequest).getId()).isEqualTo(5L);
    }

    @Test
    @DisplayName("Test for update client if existed")
    void update() {
        doReturn(Optional.of(client)).when(repository).findById(5L);
        doReturn(client).when(editMapper).updateUser(client, clientRequest);
        doReturn(clientResponse).when(viewMapper).viewUser(client);
        doReturn(client).when(repository).save(client);
        assertThat(clientService.update(clientRequest, 5L)).isEqualTo(clientResponse);
    }

    @Test
    @DisplayName("Test for get client if existed")
    void getById() {
        doReturn(Optional.of(client)).when(repository).findById(5L);
        doReturn(clientResponse).when(viewMapper).viewUser(client);
        assertThat(clientService.getById(5L)).isEqualTo(clientResponse);
    }

    @Test
    @DisplayName("Test for get all client if existed")
    void getAllClients() {
        List<ClientResponse> clientResponses = new ArrayList<>();
        clientResponses.add(clientResponse);
        doReturn(clientResponses).when(viewMapper).viewClients();

        assertThat(clientService.getAllClients().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test for get empty list if not existed")
    void getAllClientsIfNotExisted() {
        doReturn(Collections.EMPTY_LIST).when(viewMapper).viewClients();
        assertThat(clientService.getAllClients()).isEqualTo(Collections.EMPTY_LIST);
    }

    @Test
    @DisplayName("Test for delete client if existed")
    void deleteById() {
        doReturn(Optional.of(client)).when(repository).findById(5L);
        doNothing().when(repository).deleteById(5L);
        doReturn(clientResponse).when(viewMapper).viewUser(client);
        assertThat(clientService.deleteById(5L)).isEqualTo(clientResponse);
        verify(repository, times(1)).deleteById(5L);
    }
}