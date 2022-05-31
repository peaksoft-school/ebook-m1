package kg.peaksoft.ebookm1.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.api.playLoads.dto.client.ClientRequest;
import kg.peaksoft.ebookm1.api.playLoads.dto.client.ClientResponse;
import kg.peaksoft.ebookm1.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
@CrossOrigin
@PreAuthorize("hasAnyAuthority('ADMIN')")
@Tag(name = "User", description = "The User API")
public class ClientController {

    private final ClientService clientService;

    @Operation(summary = "Method update by id", description = "User with role ADMIN can update")
    @PutMapping("{id}")
    public ClientResponse updateClient(@PathVariable long id,
                                     @RequestBody ClientRequest request) {
        return clientService.update(request, id);
    }

    @Operation(summary = "Method get by id",description = "Allows all users to get a user by Id")
    @GetMapping("{id}")
    public ClientResponse getClientById(@PathVariable long id) {
        return clientService.getById(id);
    }

    @Operation(summary = "Method delete by id",description = "User with role ADMIN can delete")
    @DeleteMapping("{id}")
    public ClientResponse deleteClientById(@PathVariable long id) {
        return clientService.deleteById(id);
    }
}
