package kg.peaksoft.ebookm1.db.repository.specifications;

import kg.peaksoft.ebookm1.db.enums.Genre;
import kg.peaksoft.ebookm1.db.enums.RequestStatus;
import kg.peaksoft.ebookm1.db.enums.TypeOfBook;
import kg.peaksoft.ebookm1.db.entity.Book;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class BookSpecification  {

    public static Specification<Book> getFilter(Genre genreEnum, TypeOfBook typeOfBook){
        return ((root, query, criteriaBuilder) ->{
            List<Predicate> predicates = new ArrayList<>();
            if (genreEnum != null) {
              predicates.add(criteriaBuilder.equal(root.get("genre"),genreEnum));
            }
            if (typeOfBook != null) {
                predicates.add(criteriaBuilder.equal(root.get("typeOfBook"),typeOfBook));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
    public static Specification<Book> getByStatusAndTypeOfBook(Genre genreEnum,TypeOfBook typeOfBook,RequestStatus status){
        return ((root, query, criteriaBuilder) ->{
            List<Predicate> predicates = new ArrayList<>();

            if (genreEnum != null) {
                predicates.add(criteriaBuilder.equal(root.get("genre"),genreEnum));
            }
            if (typeOfBook != null) {
                predicates.add(criteriaBuilder.equal(root.get("typeOfBook"),typeOfBook));
            }
            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"),status));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

}
