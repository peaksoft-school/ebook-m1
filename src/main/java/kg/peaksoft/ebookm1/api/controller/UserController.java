package kg.peaksoft.ebookm1.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.api.payLoad.dto.user.UserRequest;
import kg.peaksoft.ebookm1.api.payLoad.dto.user.UserResponse;
import kg.peaksoft.ebookm1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@PreAuthorize("hasAnyAuthority('ADMIN')")
@Tag(name = "User", description = "The User API")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @Operation(summary = "A user with the Admin role can create.")
    public UserResponse createUser(@RequestBody UserRequest request) {
        return userService.create(request);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "A user with the Admin role can update.")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        return userService.update(request, id);
    }

    @GetMapping("/getById/{id}")
    public UserResponse getByIdUser(@PathVariable Long id) {
        return userService.getById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    @Operation(summary = "A user with the Admin role can delete.")
    public UserResponse deleteByIdUser(@PathVariable Long id) {
        return userService.deleteById(id);
    }

    @GetMapping("/getAll")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }
}
