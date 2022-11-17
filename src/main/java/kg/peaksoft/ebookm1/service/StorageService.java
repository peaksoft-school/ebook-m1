package kg.peaksoft.ebookm1.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageService implements kg.peaksoft.ebookm1.db.repository.StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;
    private final AmazonS3 s3;

    @Override
    public byte[] downloadFile(String fileName) {
        S3Object object = s3.getObject(bucketName, fileName);
        S3ObjectInputStream objectContent = object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(objectContent);
            log.info("The file (photo, video, audio, etc.) has been downloaded successfully: {}", Arrays.toString(content) + " - content");
            return content;
        } catch (IOException e) {
            log.error("The file (photo, video, audio, etc.) could not be downloaded: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteFile(String fileName) {
        s3.deleteObject(bucketName, fileName);
        log.info("The file was successfully deleted: {}", fileName + " - file name");
        return "The file was successfully deleted!";
    }

    @Override
    public List<String> listAllFiles() {
        ListObjectsV2Result listObjectsV2Result = s3.listObjectsV2(bucketName);
        log.info("S3 file list: {}", listObjectsV2Result);
        return listObjectsV2Result.getObjectSummaries().stream()
                .map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }

}
