package kg.peaksoft.ebookm1.api;

import kg.peaksoft.ebookm1.dto.book.BookRequest;
import kg.peaksoft.ebookm1.dto.customer.CustomerRequest;
import kg.peaksoft.ebookm1.dto.customer.CustomerResponse;
import kg.peaksoft.ebookm1.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class CustomerController {

    private final CustomerService userService;

    @PostMapping
    public CustomerResponse createUser(@RequestBody CustomerRequest request) {
        return userService.create(request);
    }

    @PostMapping("/addBook/{id}")
    public CustomerResponse addBookToVendor(@PathVariable long id,@RequestBody BookRequest request){
        return userService.addBookToVendor(request,id);
    }
    @PutMapping("{id}")
    public CustomerResponse updateUser(@PathVariable long id,
                                   @RequestBody CustomerRequest request) {
        return userService.update(request, id);
    }

    @GetMapping("{id}")
    public CustomerResponse getByIdUser(@PathVariable long id,
                                    @RequestBody CustomerRequest request) {
        return userService.getById(id);
    }

    @DeleteMapping("{id}")
    public CustomerResponse deleteByIdUser(@PathVariable long id,
                                       @RequestBody CustomerRequest request) {
        return userService.deleteById(id);
    }

    @GetMapping
    public List<CustomerResponse> getAllUsers() {
        return userService.getAllUsers();
    }

}
