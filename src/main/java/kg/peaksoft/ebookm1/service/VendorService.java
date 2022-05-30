package kg.peaksoft.ebookm1.service;

import kg.peaksoft.ebookm1.book.BookEditMapper;
import kg.peaksoft.ebookm1.dto.book.BookRequest;
import kg.peaksoft.ebookm1.dto.promocode.PromocodeRequest;
import kg.peaksoft.ebookm1.entity.Promocode;
import kg.peaksoft.ebookm1.entity.book.Book;
import kg.peaksoft.ebookm1.mapper.vendor.VendorEditMapper;
import kg.peaksoft.ebookm1.mapper.vendor.VendorViewMapper;
import kg.peaksoft.ebookm1.dto.vendor.VendorRequest;
import kg.peaksoft.ebookm1.dto.vendor.VendorResponse;
import kg.peaksoft.ebookm1.entity.User;
import kg.peaksoft.ebookm1.mapper.promocode.PromocodeEditMapper;

import kg.peaksoft.ebookm1.repository.BookRepository;
import kg.peaksoft.ebookm1.repository.PromocodeRepository;
import kg.peaksoft.ebookm1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorService {

    private final VendorEditMapper editMapper;
    private final VendorViewMapper viewMapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final BookEditMapper bookEditMapper;
    private final BookRepository bookRepository;
    private final PromocodeEditMapper promocodeEditMapper;
    private final PromocodeRepository promocodeRepository;

    public VendorResponse create(VendorRequest request) {
        User user = editMapper.createVendor(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.isActive();
        userRepository.save(user);
        return viewMapper.viewUser(user);
    }

    public VendorResponse update(VendorRequest request, Long id) {
        User user = userRepository.findById(id).get();
        editMapper.updateVendor(user, request);
        return viewMapper.viewUser(userRepository.save(user));
    }

    public VendorResponse getById(Long id) {
        User user = userRepository.findById(id).get();
        return viewMapper.viewUser(user);
    }

    public List<VendorResponse> getAllUsers() {
        return viewMapper.viewUsers(userRepository.findAll());
    }

    public VendorResponse deleteById(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.deleteById(id);
        return viewMapper.viewUser(user);
    }

    //  addBook button function section  starts  ==========================================================
    public VendorResponse addBookToVendor(BookRequest bookRequest, Long id) {
        Book book = bookEditMapper.createBook(bookRequest);
        User user = userRepository.findById(id).get();
        book.setUser(user);
        bookRepository.save(book);
        userRepository.save(user);
        return viewMapper.viewUser(user);
    }

    public VendorResponse updateBookVendor(Long userId, Long bookId, BookRequest bookRequest) {
        User user = userRepository.findById(userId).get();
        Book book = bookRepository.findById(bookId).get();
        bookEditMapper.updateBook(book, bookRequest);
        book.setUser(user);
        bookRepository.save(book);
        userRepository.save(user);
        return viewMapper.viewUser(user);
    }

    public VendorResponse deleteBookVendor(Long userId, Long bookId) {
        User user = userRepository.findById(userId).get();
        Book book = bookRepository.findById(bookId).get();
        bookRepository.delete(book);
        userRepository.save(user);
        return viewMapper.viewUser(user);
    }
    //  addBook button function section  ends  ==========================================================


    // addPromocode section starts   ====================================================

    public VendorResponse addPromocode(PromocodeRequest promocodeRequest, Long id) {
        Promocode promocode = promocodeEditMapper.create(promocodeRequest);
        User user = userRepository.findById(id).get();
        List<Book> bookList = user.getBooks();
        for (Book book : bookList
        ) {
            book.setPromocode(promocode);
        }
        promocode.setUser(user);
        promocodeRepository.save(promocode);
        userRepository.save(user);
        return viewMapper.viewUser(user);
    }
    public VendorResponse updatePromocode(PromocodeRequest promocodeRequest, Long vendorId, Long promoCodeId) {
        User user = userRepository.findById(vendorId).get();
        Promocode promocode = promocodeRepository.findById(promoCodeId).get();
        promocodeEditMapper.update(promocode,promocodeRequest);
        List<Book> bookList = user.getBooks();
        for (Book book : bookList
        ) {
            book.setPromocode(promocode);
        }
        promocode.setUser(user);
        promocodeRepository.save(promocode);
        userRepository.save(user);
        return viewMapper.viewUser(user);
    }
    public VendorResponse deletePromocode(Long vendorId, Long promoCodeId) {
        User user = userRepository.findById(vendorId).get();
        Promocode promocode = promocodeRepository.findById(promoCodeId).get();
        promocodeRepository.delete(promocode);
        userRepository.save(user);
        return viewMapper.viewUser(user);
    }


}
