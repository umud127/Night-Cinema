package az.duo.Night.Cinema.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IRestCloudinaryService {

    String uploadImage(MultipartFile imageName) throws IOException;
}
