package kg.peaksoft.ebookm1.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.api.payloads.dto.user.UserRequest;
import kg.peaksoft.ebookm1.api.payloads.dto.user.UserResponse;
import kg.peaksoft.ebookm1.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
@Tag(name = "User", description = "The User API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Method create", description = "User with role ADMIN can create")
    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request) {
        return userService.create(request);
    }

    @Operation(summary = "Method update", description = "User with role ADMIN can update")
    @PutMapping("{id}")
    public UserResponse updateUser(@PathVariable long id, @RequestBody UserRequest request) {
        return userService.update(request, id);
    }

    @Operation(summary = "Method get by id", description = "Allows all users to get a user by ID")
    @GetMapping("{id}")
    public UserResponse getByIdUser(@PathVariable long id) {
        return userService.getById(id);
    }

    @Operation(summary = "Method delete by id", description = "User with role ADMIN can delete")
    @DeleteMapping("{id}")
    public UserResponse deleteByIdUser(@PathVariable long id) {
        return userService.deleteById(id);
    }

    @Operation(summary = "Allows to get all users from the database")
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }
}
