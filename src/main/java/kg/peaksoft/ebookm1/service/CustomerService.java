package kg.peaksoft.ebookm1.service;

import kg.peaksoft.ebookm1.dto.book.BookRequest;
import kg.peaksoft.ebookm1.entity.Book;
import kg.peaksoft.ebookm1.mapper.CustomerEditMapper;
import kg.peaksoft.ebookm1.mapper.CustomerViewMapper;
import kg.peaksoft.ebookm1.dto.customer.CustomerRequest;
import kg.peaksoft.ebookm1.dto.customer.CustomerResponse;
import kg.peaksoft.ebookm1.entity.User;
import kg.peaksoft.ebookm1.mapper.book.BookEditMapper;
import kg.peaksoft.ebookm1.repository.BookRepository;
import kg.peaksoft.ebookm1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerEditMapper editMapper;
    private final CustomerViewMapper viewMapper;
    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final BookEditMapper bookEditMapper;
    private final BookRepository bookRepository;

    public CustomerResponse create(CustomerRequest request) {
        User user = editMapper.createCustomer(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        repository.save(user);
        return viewMapper.viewUser(user);
    }

    public CustomerResponse addBookToVendor(BookRequest bookRequest,Long id){
        Book book =  bookEditMapper.createNewBook(bookRequest);
        User user = repository.findById(id).get();
        book.setUser(user);
        bookRepository.save(book);
        repository.save(user);
        return viewMapper.viewUser(user);
    }

    public CustomerResponse update(CustomerRequest request, Long id) {
        User user = repository.findById(id).get();
        editMapper.updateUser(user, request);
        return viewMapper.viewUser(repository.save(user));
    }

    public CustomerResponse getById(Long id) {
        User user = repository.findById(id).get();
        return viewMapper.viewUser(user);
    }

    public List<CustomerResponse> getAllUsers() {
        return viewMapper.viewUsers(repository.findAll());
    }

    public CustomerResponse deleteById(Long id) {
        User user = repository.findById(id).get();
        repository.deleteById(id);
        return viewMapper.viewUser(user);
    }


}
