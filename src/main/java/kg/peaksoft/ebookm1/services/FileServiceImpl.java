package kg.peaksoft.ebookm1.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileServiceImpl {

    String saveFile(MultipartFile file);

    byte[] downloadFile(String fileName);

    String deleteFile(String fileName);

    List<String> listAllFiles();
}
