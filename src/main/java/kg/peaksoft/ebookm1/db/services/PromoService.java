package kg.peaksoft.ebookm1.db.services;

import kg.peaksoft.ebookm1.api.controllers.payloads.dto.book.BookResponse;
import kg.peaksoft.ebookm1.api.controllers.payloads.dto.promocode.PromocodeResponse;
import kg.peaksoft.ebookm1.db.entities.book.Book;
import kg.peaksoft.ebookm1.db.entities.others.Promocode;
import kg.peaksoft.ebookm1.db.enums.RequestStatus;
import kg.peaksoft.ebookm1.db.mappers.promocode.PromocodeViewMapper;
import kg.peaksoft.ebookm1.db.repositories.BookRepository;
import kg.peaksoft.ebookm1.db.repositories.PromocodeRepository;
import kg.peaksoft.ebookm1.db.repositories.specifications.BookSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PromoService {

    private final PromocodeRepository promocodeRepository;
    private final PromocodeViewMapper promocodeViewMapper;
    private final BookRepository bookRepository;


    public List<PromocodeResponse> getAllPromoRelatedBooks(String name, int page){
        int size =10;
        Pageable pageable = PageRequest.of(page,size);
        Specification<Promocode> filter = BookSpecification.getPromocode(name);
        return promocodeViewMapper.promocodeResponseList(promocodeRepository.findAll(filter,pageable));
    }
    public PromocodeResponse getPromocodeByName(String name){
        String text = name==null?" ":name;
        Promocode promocode= promocodeRepository.findPromocodeByPromoName(text.toLowerCase(Locale.ROOT));
        List<Book> book = bookRepository.findAllByStatus(RequestStatus.APPROVED);
        List<Book> approvedBooks = new ArrayList<>();
        for (Book approved:book
             ) {
            approvedBooks.add(approved);
        }
        promocode.setBooks(approvedBooks);

        return promocodeViewMapper.viewPromoMapper(promocode);
    }
}
