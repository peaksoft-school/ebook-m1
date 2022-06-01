package kg.peaksoft.ebookm1.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.api.playLoads.dto.admin.AdminRequest;
import kg.peaksoft.ebookm1.api.playLoads.dto.admin.AdminResponse;
import kg.peaksoft.ebookm1.api.playLoads.dto.auth.AuthRequest;
import kg.peaksoft.ebookm1.api.playLoads.dto.auth.AuthResponse;
import kg.peaksoft.ebookm1.api.playLoads.dto.client.ClientRequest;
import kg.peaksoft.ebookm1.api.playLoads.dto.client.ClientResponse;
import kg.peaksoft.ebookm1.api.playLoads.dto.vendor.VendorRequest;
import kg.peaksoft.ebookm1.api.playLoads.dto.vendor.VendorResponse;
import kg.peaksoft.ebookm1.api.playLoads.exception.ExceptionType;
import kg.peaksoft.ebookm1.config.JwtTokenUtil;
import kg.peaksoft.ebookm1.dataBase.entity.User;
import kg.peaksoft.ebookm1.dataBase.mapper.auth.AuthMapper;
import kg.peaksoft.ebookm1.dataBase.repository.UserRepository;
import kg.peaksoft.ebookm1.service.AdminService;
import kg.peaksoft.ebookm1.service.ClientService;
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
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Tag(name = "Authentication", description = "User with role ADMIN can authenticate")
public class AuthController {
    private final ClientService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository repository;
    private final AuthMapper authMapper;
    private final VendorService vendorService;
    private final AdminService adminService;

    @Operation(summary = "All users can authenticate", description = "login all users")
    @PostMapping("/login")
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

    @Operation(summary = "Method create client", description = "Sign up/sign customer layout")
    @PostMapping("/customer")
    public ClientResponse createClient(@RequestBody ClientRequest userRequest) {
        return userService.createClient(userRequest);
    }

    @Operation(summary = "Method create vendor", description = "Sign up/sign vendor layout")
    @PostMapping("/vendor")
    public VendorResponse createVendor(@RequestBody VendorRequest request) {
        return vendorService.create(request);
    }

    @Operation(summary = "Method create admin", description = "Sign up/sign admin layout")
    @PostMapping("/admin")
    public AdminResponse createAdmin(@RequestBody AdminRequest request) {
        return adminService.createAdmin(request);
    }
}
