package kg.peaksoft.ebookm1.dataBase.entities.bookSpecification;

import kg.peaksoft.ebookm1.api.payloads.dto.enums.Genere;
import kg.peaksoft.ebookm1.api.payloads.dto.enums.TypeOfBook;
import kg.peaksoft.ebookm1.dataBase.entities.book.Book;
import kg.peaksoft.ebookm1.dataBase.entities.book.Genre;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class BookSpecification  {

    public static Specification<Book> getFilter(Genere genere, TypeOfBook typeOfBook){
        return ((root, query, criteriaBuilder) ->{
            List<Predicate> predicates = new ArrayList<>();
            if (genere != null) {
              predicates.add(criteriaBuilder.equal(root.get("genere"),genere));
            }
            if (typeOfBook != null) {
                predicates.add(criteriaBuilder.equal(root.get("typeOfBook"),typeOfBook));
            }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

}
