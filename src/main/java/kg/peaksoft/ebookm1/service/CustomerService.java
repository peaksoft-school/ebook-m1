package kg.peaksoft.ebookm1.service;

import kg.peaksoft.ebookm1.dto.book.BookRequest;
import kg.peaksoft.ebookm1.dto.promocode.PromocodeRequest;
import kg.peaksoft.ebookm1.entity.Book;
import kg.peaksoft.ebookm1.entity.Promocode;
import kg.peaksoft.ebookm1.mapper.customer.CustomerEditMapper;
import kg.peaksoft.ebookm1.mapper.customer.CustomerViewMapper;
import kg.peaksoft.ebookm1.dto.customer.CustomerRequest;
import kg.peaksoft.ebookm1.dto.customer.CustomerResponse;
import kg.peaksoft.ebookm1.entity.User;
import kg.peaksoft.ebookm1.mapper.book.BookEditMapper;
import kg.peaksoft.ebookm1.mapper.promocode.PromocodeEditMapper;
import kg.peaksoft.ebookm1.repository.BookRepository;
import kg.peaksoft.ebookm1.repository.PromocodeRepository;
import kg.peaksoft.ebookm1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerEditMapper editMapper;
    private final CustomerViewMapper viewMapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final BookEditMapper bookEditMapper;
    private final BookRepository bookRepository;
    private final PromocodeEditMapper promocodeEditMapper;
    private final PromocodeRepository promocodeRepository;

    public CustomerResponse create(CustomerRequest request) {
        User user = editMapper.createCustomer(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return viewMapper.viewUser(user);
    }
 //  addBook button function section  starts  ==========================================================
    public CustomerResponse addBookToVendor(BookRequest bookRequest,Long id){
        Book book =  bookEditMapper.createNewBook(bookRequest);
        User user = userRepository.findById(id).get();
        book.setUser(user);
        bookRepository.save(book);
        userRepository.save(user);
        return viewMapper.viewUser(user);
    }
    public CustomerResponse updateBookVendor(Long userId, Long bookId, BookRequest bookRequest){
        User user = userRepository.findById(userId).get();
        Book book = bookRepository.findById(bookId).get();
        bookEditMapper.updateBook(book,bookRequest);
        book.setUser(user);
        bookRepository.save(book);
        userRepository.save(user);
        return viewMapper.viewUser(user);
    }
    public CustomerResponse deleteBookVendor(Long userId, Long bookId){
        User user = userRepository.findById(userId).get();
        Book book = bookRepository.findById(bookId).get();
        bookRepository.delete(book);
        userRepository.save(user);
        return viewMapper.viewUser(user);

    }

    //  addBook button function section  ends  ==========================================================


    // addPromocode section starts   ====================================================


    public CustomerResponse addPromocode(PromocodeRequest promocodeRequest,Long id){
        Promocode promocode = promocodeEditMapper.create(promocodeRequest);
        User user = userRepository.findById(id).get();
        List<Book> bookList = user.getBooks();

        for (Book book:bookList
             ) {
            book.setPromocode(promocode);

        }

        promocode.setUser(user);
        promocodeRepository.save(promocode);
        userRepository.save(user);
        return viewMapper.viewUser(user);
    }

    public CustomerResponse update(CustomerRequest request, Long id) {
        User user = userRepository.findById(id).get();
        editMapper.updateUser(user, request);
        return viewMapper.viewUser(userRepository.save(user));
    }

    public CustomerResponse getById(Long id) {
        User user = userRepository.findById(id).get();
        return viewMapper.viewUser(user);
    }

    public List<CustomerResponse> getAllUsers() {
        return viewMapper.viewUsers(userRepository.findAll());
    }

    public CustomerResponse deleteById(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.deleteById(id);
        return viewMapper.viewUser(user);
    }


}
