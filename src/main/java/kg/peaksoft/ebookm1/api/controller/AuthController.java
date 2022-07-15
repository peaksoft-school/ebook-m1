package kg.peaksoft.ebookm1.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.api.payload.auth.AuthRequest;
import kg.peaksoft.ebookm1.api.payload.auth.AuthResponse;
import kg.peaksoft.ebookm1.api.payload.client.ClientRequest;
import kg.peaksoft.ebookm1.api.payload.client.ClientResponse;
import kg.peaksoft.ebookm1.api.payload.vendor.VendorRequest;
import kg.peaksoft.ebookm1.api.payload.vendor.VendorResponse;
import kg.peaksoft.ebookm1.config.security.JwtTokenUtil;
import kg.peaksoft.ebookm1.db.entity.security.User;
import kg.peaksoft.ebookm1.db.mapper.AuthMapper;
import kg.peaksoft.ebookm1.db.repository.UserRepository;
import kg.peaksoft.ebookm1.db.services.ClientService;
import kg.peaksoft.ebookm1.db.services.VendorService;
import kg.peaksoft.ebookm1.exceptions.ExceptionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Tag(name = "Authentication", description = "User with role ADMIN can authenticate")
public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository repository;
    private final AuthMapper authMapper;
    private final VendorService vendorService;
    private final ClientService userService;

    @PostMapping("/login")
    @Operation(summary = "All users can authenticate", description = "login all users")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            User user = repository.findByEmail(authenticationToken.getName()).get();
            log.info("Inside AuthController get login method");
            return ResponseEntity.ok()
                    .body(authMapper.view(jwtTokenUtil.generateToken(user), ExceptionType.SUCCESSFULLY, user));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authMapper.view("", ExceptionType.LOGIN_FAILED, null));
        }
    }

    @Operation(summary = "Method registration vendor", description = "Sign up/sign vendor layout")
    @PostMapping("/vendor")
    public VendorResponse registration(@RequestBody VendorRequest request) {
        log.info("Inside AuthController registration vendor method");
        return vendorService.create(request);
    }

    @Operation(summary = "Method registration client", description = "Registration client layout")
    @PostMapping("/client")
    public ClientResponse registration(@RequestBody ClientRequest request) {
        log.info("Inside AuthController registration client method");
        return userService.registration(request);
    }
}
