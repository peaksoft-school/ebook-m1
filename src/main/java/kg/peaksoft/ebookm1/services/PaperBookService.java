package kg.peaksoft.ebookm1.services;

import kg.peaksoft.ebookm1.dataBase.entities.book.PaperBook;
import kg.peaksoft.ebookm1.dataBase.repositories.PaperBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaperBookService {

    private final PaperBookRepository paperBookRepository;

    public List<PaperBook> getAllPaperBooks() {
        return paperBookRepository.findAll();
    }
}
