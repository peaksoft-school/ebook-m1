package kg.peaksoft.ebookm1.db.repository;

import java.util.List;

public interface StorageService {

    byte[] downloadFile(String fileName);

    String deleteFile(String fileName);

    List<String> listAllFiles();

}
