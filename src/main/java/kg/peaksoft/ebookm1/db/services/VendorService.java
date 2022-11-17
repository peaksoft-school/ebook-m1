package kg.peaksoft.ebookm1.db.services;

import kg.peaksoft.ebookm1.db.enums.RequestStatus;
import kg.peaksoft.ebookm1.db.mapper.BookEditMapper;
import kg.peaksoft.ebookm1.api.payload.book.BookRequest;
import kg.peaksoft.ebookm1.api.payload.promocode.PromoCodeRequest;
import kg.peaksoft.ebookm1.api.payload.vendor.VendorRequest;
import kg.peaksoft.ebookm1.api.payload.vendor.VendorResponse;
import kg.peaksoft.ebookm1.db.entity.PromoCode;
import kg.peaksoft.ebookm1.db.entity.User;
import kg.peaksoft.ebookm1.db.entity.Book;
import kg.peaksoft.ebookm1.db.mapper.PromoCodeEditMapper;
import kg.peaksoft.ebookm1.db.mapper.VendorEditMapper;
import kg.peaksoft.ebookm1.db.mapper.VendorViewMapper;
import kg.peaksoft.ebookm1.db.repository.BookRepository;
import kg.peaksoft.ebookm1.db.repository.PromoCodeRepository;
import kg.peaksoft.ebookm1.db.repository.UserRepository;
import kg.peaksoft.ebookm1.exceptions.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VendorService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final VendorEditMapper editMapper;
    private final VendorViewMapper viewMapper;
    private final BookEditMapper bookEditMapper;
    private final BookRepository bookRepository;
    private final PromoCodeEditMapper promocodeEditMapper;
    private final PromoCodeRepository promocodeRepository;

    public VendorResponse register(VendorRequest request) {
        User vendor = editMapper.createVendor(request);
        vendor.setPassword(passwordEncoder.encode(request.getPassword()));
        vendor.isActive();
        repository.save(vendor);
        log.info("The vendor has successfully registered: {}", vendor.getFirstName());
        return viewMapper.viewVendor(vendor);
    }

    public VendorResponse update(Long id, VendorRequest request) {
        User vendor = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException(User.class, id));
        editMapper.updateVendor(vendor, request);
        log.info("The vendor has successfully updated his data: {}", vendor.getFirstName());
        return viewMapper.viewVendor(repository.save(vendor));
    }

    public VendorResponse deleteById(Long id) {
        User vendor = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException(User.class, id));
        repository.deleteById(id);
        log.info("The vendor was successfully removed by ID from the database: {}", vendor.getFirstName());
        return viewMapper.viewVendor(vendor);
    }

    public List<VendorResponse> getAllVendors() {
        log.info("Getting all vendors: ");
        return viewMapper.viewVendors();
    }

    public VendorResponse gitById(Long id) {
        User vendor = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException(User.class, id));
        log.info("getting a vendor by id: {}", vendor.getFirstName());
        return viewMapper.viewVendorById(vendor);
    }

    //  addBook button function section
    public VendorResponse addBookToVendor(BookRequest bookRequest, Long id) {
        Book book = bookEditMapper.createBook(bookRequest);
        book.setStatus(RequestStatus.SUBMITTED);
        User user = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException(User.class, id));
        book.setUser(user);
        bookRepository.save(book);
        repository.save(user);
        log.info("The vendor has successfully added his book to the database: {}", book.getTitle());
        return viewMapper.viewVendor(user);
    }

    public VendorResponse updateBookVendor(Long userId, Long bookId, BookRequest bookRequest) {
        User user = repository.findById(userId).orElseThrow(() ->
                new NoSuchElementException(User.class, userId));
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new NoSuchElementException(Book.class, bookId));
        bookEditMapper.updateBook(book, bookRequest);
        book.setUser(user);
        book.setStatus(RequestStatus.SUBMITTED);
        bookRepository.save(book);
        repository.save(user);
        log.info("The vendor has successfully updated the book data: {}", book.getTitle());
        return viewMapper.viewVendor(user);
    }

    public VendorResponse deleteBookVendor(Long userId, Long bookId) {
        User user = repository.findById(userId).orElseThrow(() ->
                new NoSuchElementException(User.class, userId));
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new NoSuchElementException(Book.class, bookId));
        bookRepository.deleteById(bookId);
        repository.save(user);
        log.info("The vendor has successfully deleted his book: {}", book.getTitle());
        return viewMapper.viewVendor(user);
    }
    //  addBook button function section  ends  ==========================================================

    // addPromocode section starts   ====================================================
    public VendorResponse addPromocode(PromoCodeRequest promocodeRequest, Long id) {
        PromoCode promocode = promocodeEditMapper.create(promocodeRequest);
        User user = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException(User.class, id));
        List<Book> bookList = user.getBooks();
        for (Book book : bookList) {
            book.setPromoCode(promocode);
        }
        promocode.setUser(user);
        promocodeRepository.save(promocode);
        repository.save(user);
        log.info("The vendor has successfully added a promo code: {}", promocode.getPromoName());
        return viewMapper.viewVendor(user);
    }

    public VendorResponse updatePromocode(PromoCodeRequest promocodeRequest, Long vendorId, Long promoCodeId) {
        User user = repository.findById(vendorId).orElseThrow(() ->
                new NoSuchElementException(User.class, vendorId));
        PromoCode promocode = promocodeRepository.findById(promoCodeId).orElseThrow(() ->
                new NoSuchElementException(PromoCode.class, promoCodeId));
        promocodeEditMapper.update(promocode, promocodeRequest);
        List<Book> bookList = user.getBooks();
        for (Book book : bookList) {
            book.setPromoCode(promocode);
        }
        promocode.setUser(user);
        promocodeRepository.save(promocode);
        repository.save(user);
        log.info("The vendor has successfully updated the promo code: {}", promocode.getPromoName());
        return viewMapper.viewVendor(user);
    }

    public VendorResponse deletePromocode(Long vendorId, Long promoCodeId) {
        User user = repository.findById(vendorId).orElseThrow(() ->
                new NoSuchElementException(User.class, vendorId));
        PromoCode promocode = promocodeRepository.findById(promoCodeId).orElseThrow(() ->
                new NoSuchElementException(PromoCode.class, promoCodeId));
        promocodeRepository.delete(promocode);
        repository.save(user);
        log.info("The seller has successfully deleted the promo code: {}", promocode.getPromoName());
        return viewMapper.viewVendor(user);
    }
}
