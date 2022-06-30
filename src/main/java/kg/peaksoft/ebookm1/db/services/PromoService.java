package kg.peaksoft.ebookm1.db.services;

import kg.peaksoft.ebookm1.api.controllers.payloads.dto.promocode.PromocodeResponse;
import kg.peaksoft.ebookm1.db.entities.book.Book;
import kg.peaksoft.ebookm1.db.entities.others.Promocode;
import kg.peaksoft.ebookm1.db.enums.RequestStatus;
import kg.peaksoft.ebookm1.db.mappers.promocode.PromocodeViewMapper;
import kg.peaksoft.ebookm1.db.repositories.BookRepository;
import kg.peaksoft.ebookm1.db.repositories.PromocodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromoService {

    private final PromocodeRepository promocodeRepository;
    private final PromocodeViewMapper promocodeViewMapper;
    private final BookRepository bookRepository;


    public PromocodeResponse getPromocodeByName(String name) {
        String text = name == null ? " " : name;
        Promocode promocode = promocodeRepository.findPromocodeByPromoName(text.toLowerCase(Locale.ROOT));
        List<Book> book = bookRepository.findAllByStatus(RequestStatus.APPROVED);
        List<Book> approvedBooks = new ArrayList<>();
        for (Book approved : book
        ) {
            approvedBooks.add(approved);
        }
        promocode.setBooks(approvedBooks);
        log.info("All approved books with related promo code:");
        return promocodeViewMapper.viewPromoMapper(promocode);
    }
}
