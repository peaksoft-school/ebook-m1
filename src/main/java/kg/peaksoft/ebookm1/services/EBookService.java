package kg.peaksoft.ebookm1.services;

import kg.peaksoft.ebookm1.dataBase.entities.book.EBook;
import kg.peaksoft.ebookm1.dataBase.repositories.EBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EBookService {

    private final EBookRepository eBookRepository;

    public List<EBook> getAllEBooks() {
        return eBookRepository.findAll();
    }
}
