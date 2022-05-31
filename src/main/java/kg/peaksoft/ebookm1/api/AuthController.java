package kg.peaksoft.ebookm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.config.JwtTokenUtil;
import kg.peaksoft.ebookm1.dto.admin.AdminRequest;
import kg.peaksoft.ebookm1.dto.admin.AdminResponse;
import kg.peaksoft.ebookm1.dto.user.UserRequest;
import kg.peaksoft.ebookm1.dto.user.UserResponse;
import kg.peaksoft.ebookm1.dto.auth.AuthMapper;
import kg.peaksoft.ebookm1.dto.auth.AuthRequest;
import kg.peaksoft.ebookm1.dto.auth.AuthResponse;
import kg.peaksoft.ebookm1.dto.vendor.VendorRequest;
import kg.peaksoft.ebookm1.dto.vendor.VendorResponse;
import kg.peaksoft.ebookm1.entity.User;
import kg.peaksoft.ebookm1.exception.ExceptionType;
import kg.peaksoft.ebookm1.repository.UserRepository;
import kg.peaksoft.ebookm1.service.AdminService;
import kg.peaksoft.ebookm1.service.UserService;
import kg.peaksoft.ebookm1.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
@CrossOrigin
@Tag(name = "Authentication", description = "User with role ADMIN can authenticate")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository repository;
    private final AuthMapper authMapper;
    private final VendorService vendorService;
    private final AdminService adminService;

    @PostMapping("/login")
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

    @PostMapping("/customer")
    @Operation(summary = "All users can register", description = "Sign up/sign customer layout")
    public UserResponse create(@RequestBody UserRequest userRequest) {
        return userService.createClient(userRequest);
    }

    @Operation(summary = "Method create", description = "Sign up/sign vendor layout")
    @PostMapping("/vendor")
    public VendorResponse create(@RequestBody VendorRequest request) {
        return vendorService.create(request);
    }

    @Operation(summary = "Method create admin", description = "Sign up/sign admin layout")
    @PostMapping("/admin")
    public AdminResponse createAdmin(@RequestBody AdminRequest request) {
        return adminService.createAdmin(request);
    }
}
