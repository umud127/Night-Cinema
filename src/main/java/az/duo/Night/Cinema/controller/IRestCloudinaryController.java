package az.duo.Night.Cinema.controller;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IRestCloudinaryController {

    String uploadImage(String token, MultipartFile imageName) throws IOException;
}
