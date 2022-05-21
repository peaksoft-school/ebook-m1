package kg.peaksoft.ebookm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.config.JwtTokenUtil;
import kg.peaksoft.ebookm1.dto.user.UserRequest;
import kg.peaksoft.ebookm1.dto.user.UserResponse;
import kg.peaksoft.ebookm1.dto.auth.AuthMapper;
import kg.peaksoft.ebookm1.dto.auth.AuthRequest;
import kg.peaksoft.ebookm1.dto.auth.AuthResponse;
import kg.peaksoft.ebookm1.entity.User;
import kg.peaksoft.ebookm1.exception.ExceptionType;
import kg.peaksoft.ebookm1.repository.UserRepository;
import kg.peaksoft.ebookm1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
@Tag(name = "Authentication",description = "User with role ADMIN can authenticate")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository repository;
    private final AuthMapper authMapper;

    @PostMapping("login")
    @Operation(summary = "All users can authenticate", description = "login all users")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            User user = repository.findByEmail(authenticationToken.getName()).get();
            return ResponseEntity.ok()
                    .body(authMapper.view(jwtTokenUtil.generateToken(user), ExceptionType.SUCCESSFULLY, user));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authMapper.view("", ExceptionType.LOGIN_FAILED, null));

        }
    }

    @PostMapping("registration")
    @Operation(summary = "All users can registration", description = "user can registration")
    public UserResponse create(@RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }


}
