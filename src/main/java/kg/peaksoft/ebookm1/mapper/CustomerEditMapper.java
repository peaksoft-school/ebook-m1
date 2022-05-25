package kg.peaksoft.ebookm1.mapper;

import kg.peaksoft.ebookm1.dto.book.BookRequest;
import kg.peaksoft.ebookm1.dto.customer.CustomerRequest;
import kg.peaksoft.ebookm1.entity.Book;
import kg.peaksoft.ebookm1.entity.Role;
import kg.peaksoft.ebookm1.entity.User;
import kg.peaksoft.ebookm1.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerEditMapper {

    private final RoleRepository roleRepository;

    public User createCustomer(CustomerRequest request) {
        if (request == null) {
            return null;
        }
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        List<Role> roleList = new ArrayList<>();
        Role role  = roleRepository.findById(2L).get();
        roleList.add(role);
        user.setRoles(roleList);
        return user;
    }

    public User updateUser(User user, CustomerRequest customerRequest) {
        user.setFirstName(customerRequest.getFirstName());
        user.setLastName(customerRequest.getLastName());
        user.setEmail(customerRequest.getEmail());
        user.setPassword(customerRequest.getPassword());
        return user;
    }
    public User addBook(User user, BookRequest bookRequest) {
        List<Book> bookList = new ArrayList<>();
        Book book = new Book();
        bookList.add(book);
        user.setBooks(bookList);
        return user;
    }
}
