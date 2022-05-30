package kg.peaksoft.ebookm1.api;

import kg.peaksoft.ebookm1.dto.book.BookRequest;
import kg.peaksoft.ebookm1.dto.customer.VendorResponse;
import kg.peaksoft.ebookm1.dto.customer.VendorRequest;
import kg.peaksoft.ebookm1.dto.promocode.PromocodeRequest;
import kg.peaksoft.ebookm1.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class VendorController {

    private final VendorService userService;

    @PostMapping
    public VendorResponse createUser(@RequestBody VendorRequest request) {
        return userService.create(request);
    }

    @PostMapping("/addBook/{id}")
    public VendorResponse addBookToVendor(@PathVariable long id, @RequestBody BookRequest request){
        return userService.addBookToVendor(request,id);
    }
    @PutMapping("/updateBook/{customerId}/{bookId}")
    public VendorResponse updateBook(@PathVariable long customerId, @PathVariable long bookId, @RequestBody BookRequest request){
        return userService.updateBookVendor(customerId,bookId,request);
    }
    @DeleteMapping("/deleteBook/{customerId}/{bookId}")
    public VendorResponse deleteBook(@PathVariable long customerId, @PathVariable long bookId){
        return userService.deleteBookVendor(customerId,bookId);
    }


    @PostMapping("/addPromocode/{id}")
    public VendorResponse addPromocode(@PathVariable long id, @RequestBody PromocodeRequest promocodeRequest){
        return userService.addPromocode(promocodeRequest,id);
    }
    @PutMapping("{id}")
    public VendorResponse updateUser(@PathVariable long id,
                                     @RequestBody VendorRequest request) {
        return userService.update(request, id);
    }

    @GetMapping("{id}")
    public VendorResponse getByIdUser(@PathVariable long id,
                                      @RequestBody VendorRequest request) {
        return userService.getById(id);
    }

    @DeleteMapping("{id}")
    public VendorResponse deleteByIdUser(@PathVariable long id,
                                         @RequestBody VendorRequest request) {
        return userService.deleteById(id);
    }

    @GetMapping
    public List<VendorResponse> getAllUsers() {
        return userService.getAllUsers();
    }

}
