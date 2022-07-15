package kg.peaksoft.ebookm1.api.controller;

import com.amazonaws.services.s3.AmazonS3;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.ebookm1.service.AWSS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
@Tag(name = "AWS S3", description = "The AWS S3 API")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class FileController {

    private final AWSS3Service s3Service;
    private final AmazonS3 s3;
    @Value("${bucketName}")
    private String bucketName;

    @PreAuthorize("hasAnyAuthority('ROLE_VENDOR')")
    @Operation(summary = "Method upload", description = "Vendor can to upload file the bucket s3")
    @PostMapping
    public ResponseEntity<String> upload(@RequestParam MultipartFile file) throws IOException {
        File modifiedFile = new File(file.getOriginalFilename());
        FileOutputStream outputStream = new FileOutputStream(modifiedFile);
        outputStream.write(file.getBytes());
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        s3.putObject(bucketName, fileName, modifiedFile);
        modifiedFile.delete();
        log.info("Inside S3Controller Method of uploading a file to s3 server");
        return new ResponseEntity<>("The file has been uploaded successfully: " + fileName, HttpStatus.OK);
    }

    @Operation(summary = "Method download file", description = "Allows to download file from the bucket s3")
    @GetMapping("{fileName}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable("fileName") String fileName) {
        byte[] data = s3Service.downloadFile(fileName);
        ByteArrayResource arrayResource = new ByteArrayResource(data);
        log.info("Inside the S3Controller, the method of downloading a file from the s3 server");
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; fileName=\"" + fileName + "\"")
                .body(arrayResource);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_VENDOR')")
    @Operation(summary = "Method delete file", description = "Vendor can delete the file from the bucket s3")
    @DeleteMapping
    public String deleteFile(@RequestParam String fileName) {
        log.info("Inside the S3Сontroller, the method is deleting a file from the s3 server");
        return s3Service.deleteFile(fileName);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_VENDOR')")
    @Operation(summary = "Method get all files", description = "Vendor get all files from the bucket s3")
    @GetMapping
    public List<String> getAllFiles() {
        log.info("Inside S3Controller Method of getting all files from s3 server");
        return s3Service.listAllFiles();
    }
}
