package kg.peaksoft.ebookm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.dto.user.UserRequest;
import kg.peaksoft.ebookm1.dto.user.UserResponse;
import kg.peaksoft.ebookm1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin
@PreAuthorize("hasAnyAuthority('ADMIN')")
@Tag(name = "User", description = "The User API")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @Operation(summary = "User with role ADMIN can create")
    public UserResponse createUser(@RequestBody UserRequest request) {
        return userService.create(request);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "User with role ADMIN can update")
    public UserResponse updateUser(@PathVariable long id,
                                   @RequestBody UserRequest request) {
        return userService.update(request, id);
    }

    @GetMapping("/getById/{id}")
    @Operation(summary = "Allows all users to get a user by ID")
    public UserResponse getByIdUser(@PathVariable long id,
                                    @RequestBody UserRequest request) {
        return userService.getById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    @Operation(summary = "User with role ADMIN can delete")
    public UserResponse deleteByIdUser(@PathVariable long id,
                                       @RequestBody UserRequest request) {
        return userService.deleteById(id);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Allows to get all users from the database")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

}
