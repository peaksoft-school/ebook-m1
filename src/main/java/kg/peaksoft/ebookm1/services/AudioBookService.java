package kg.peaksoft.ebookm1.services;

import kg.peaksoft.ebookm1.dataBase.entities.book.AudioBook;
import kg.peaksoft.ebookm1.dataBase.repositories.AudioBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AudioBookService {

    private final AudioBookRepository audioBookRepository;

    public List<AudioBook> getAllAudioBooks() {
        return audioBookRepository.findAll();
    }
}
