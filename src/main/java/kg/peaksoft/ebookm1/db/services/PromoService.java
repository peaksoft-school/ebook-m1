package kg.peaksoft.ebookm1.db.services;

import kg.peaksoft.ebookm1.api.payload.promocode.PromoCodeResponse;
import kg.peaksoft.ebookm1.db.entity.Book;
import kg.peaksoft.ebookm1.db.entity.PromoCode;
import kg.peaksoft.ebookm1.db.enums.RequestStatus;
import kg.peaksoft.ebookm1.db.mapper.PromoCodeViewMapper;
import kg.peaksoft.ebookm1.db.repository.BookRepository;
import kg.peaksoft.ebookm1.db.repository.PromoCodeRepository;
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

    private final PromoCodeRepository promocodeRepository;
    private final PromoCodeViewMapper promocodeViewMapper;
    private final BookRepository bookRepository;

    public PromoCodeResponse getPromoCodeByName(String name) {
        String text = name == null ? " " : name;
        PromoCode promocode = promocodeRepository.findPromoCodeByPromoName(text.toLowerCase(Locale.ROOT));
        List<Book> book = bookRepository.findAllByStatus(RequestStatus.APPROVED);
        List<Book> approvedBooks = new ArrayList<>(book);
        promocode.setBooks(approvedBooks);
        log.info("All approved books with related promo code:");
        return promocodeViewMapper.viewPromoMapper(promocode);
    }

}
