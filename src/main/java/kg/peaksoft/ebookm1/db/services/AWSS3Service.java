package kg.peaksoft.ebookm1.db.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AWSS3Service implements FileServiceImpl {

    @Value("${bucketName}")
    private String bucketName;
    private AmazonS3 s3;

    public AWSS3Service(AmazonS3 s3) {
        this.s3 = s3;
    }

    @Override
    public String saveFile(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        try {
            File file1 = convertMultiPartToFile(file);
            PutObjectResult putObjectResult = s3.putObject(bucketName, originalFileName, file1);
            return putObjectResult.getContentMd5();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] downloadFile(String fileName) {
        S3Object object = s3.getObject(bucketName, fileName);
        S3ObjectInputStream objectContent = object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(objectContent);
            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteFile(String fileName) {
        s3.deleteObject(bucketName, fileName);
        return "The file was successfully deleted!";
    }

    @Override
    public List<String> listAllFiles() {
        ListObjectsV2Result listObjectsV2Result = s3.listObjectsV2(bucketName);
        return listObjectsV2Result.getObjectSummaries().stream()
                .map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
