package kg.peaksoft.ebookm1.api;

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
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request) {
        return userService.create(request);
    }

    @PutMapping("{id}")
    public UserResponse updateUser(@PathVariable long id,
                                   @RequestBody UserRequest request) {
        return userService.update(request, id);
    }

    @GetMapping("{id}")
    public UserResponse getByIdUser(@PathVariable long id,
                                    @RequestBody UserRequest request) {
        return userService.getById(id);
    }

    @DeleteMapping("{id}")
    public UserResponse deleteByIdUser(@PathVariable long id,
                                       @RequestBody UserRequest request) {
        return userService.deleteById(id);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

}
