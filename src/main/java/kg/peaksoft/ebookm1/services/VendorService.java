package kg.peaksoft.ebookm1.services;

import kg.peaksoft.ebookm1.api.payloads.dto.enums.RequestStatus;
import kg.peaksoft.ebookm1.dataBase.mappers.book.BookEditMapper;
import kg.peaksoft.ebookm1.api.payloads.dto.book.BookRequest;
import kg.peaksoft.ebookm1.api.payloads.dto.promocode.PromocodeRequest;
import kg.peaksoft.ebookm1.api.payloads.dto.vendor.VendorRequest;
import kg.peaksoft.ebookm1.api.payloads.dto.vendor.VendorResponse;
import kg.peaksoft.ebookm1.dataBase.entities.others.Promocode;
import kg.peaksoft.ebookm1.dataBase.entities.security.User;
import kg.peaksoft.ebookm1.dataBase.entities.book.Book;
import kg.peaksoft.ebookm1.dataBase.mappers.promocode.PromocodeEditMapper;
import kg.peaksoft.ebookm1.dataBase.mappers.vendor.VendorEditMapper;
import kg.peaksoft.ebookm1.dataBase.mappers.vendor.VendorViewMapper;
import kg.peaksoft.ebookm1.dataBase.repositories.BookRepository;
import kg.peaksoft.ebookm1.dataBase.repositories.PromocodeRepository;
import kg.peaksoft.ebookm1.dataBase.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final VendorEditMapper editMapper;
    private final VendorViewMapper viewMapper;
    private final BookEditMapper bookEditMapper;
    private final BookRepository bookRepository;
    private final PromocodeEditMapper promocodeEditMapper;
    private final PromocodeRepository promocodeRepository;

    public VendorResponse create(VendorRequest request) {
        User vendor = editMapper.createVendor(request);
        vendor.setPassword(passwordEncoder.encode(request.getPassword()));
        vendor.isActive();
        repository.save(vendor);
        return viewMapper.viewVendor(vendor);
    }

    public VendorResponse update(Long id, VendorRequest request) {
        User vendor = repository.findById(id).get();
        editMapper.updateVendor(vendor, request);
        return viewMapper.viewVendor(repository.save(vendor));
    }

    public VendorResponse deleteById(Long id) {
        User vendor = repository.findById(id).get();
        repository.deleteById(id);
        return viewMapper.viewVendor(vendor);
    }

    public List<VendorResponse> getAllVendors() {
        return viewMapper.viewVendors();
    }

    //  addBook button function section  starts  ==========================================================
    public VendorResponse addBookToVendor(BookRequest bookRequest, Long id) {
        Book book = bookEditMapper.createBook(bookRequest);
        book.setStatus(RequestStatus.SUBMITTED);
        User user = repository.findById(id).get();
        book.setUser(user);
        bookRepository.save(book);
        repository.save(user);
        return viewMapper.viewVendor(user);
    }

    public VendorResponse updateBookVendor(Long userId, Long bookId, BookRequest bookRequest) {
        User user = repository.findById(userId).get();
        Book book = bookRepository.findById(bookId).get();
        bookEditMapper.updateBook(book, bookRequest);
        book.setUser(user);
        bookRepository.save(book);
        repository.save(user);
        return viewMapper.viewVendor(user);
    }

    public VendorResponse deleteBookVendor(Long userId, Long bookId) {
        User user = repository.findById(userId).get();
        Book book = bookRepository.findById(bookId).get();
        bookRepository.delete(book);
        repository.save(user);
        return viewMapper.viewVendor(user);
    }
    //  addBook button function section  ends  ==========================================================

    // addPromocode section starts   ====================================================
    public VendorResponse addPromocode(PromocodeRequest promocodeRequest, Long id) {
        Promocode promocode = promocodeEditMapper.create(promocodeRequest);
        User user = repository.findById(id).get();
        List<Book> bookList = user.getBooks();
        for (Book book : bookList) {
            book.setPromocode(promocode);
        }
        promocode.setUser(user);
        promocodeRepository.save(promocode);
        repository.save(user);
        return viewMapper.viewVendor(user);
    }

    public VendorResponse updatePromocode(PromocodeRequest promocodeRequest, Long vendorId, Long promoCodeId) {
        User user = repository.findById(vendorId).get();
        Promocode promocode = promocodeRepository.findById(promoCodeId).get();
        promocodeEditMapper.update(promocode, promocodeRequest);
        List<Book> bookList = user.getBooks();
        for (Book book : bookList) {
            book.setPromocode(promocode);
        }
        promocode.setUser(user);
        promocodeRepository.save(promocode);
        repository.save(user);
        return viewMapper.viewVendor(user);
    }

    public VendorResponse deletePromocode(Long vendorId, Long promoCodeId) {
        User user = repository.findById(vendorId).get();
        Promocode promocode = promocodeRepository.findById(promoCodeId).get();
        promocodeRepository.delete(promocode);
        repository.save(user);
        return viewMapper.viewVendor(user);
    }
}
