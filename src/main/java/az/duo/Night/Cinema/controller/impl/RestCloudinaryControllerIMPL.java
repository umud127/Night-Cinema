package az.duo.Night.Cinema.controller.impl;

import az.duo.Night.Cinema.controller.IRestCloudinaryController;
import az.duo.Night.Cinema.service.IRestCloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/cloudinary")
@RequiredArgsConstructor
public class RestCloudinaryControllerIMPL implements IRestCloudinaryController {

    private final IRestCloudinaryService restCloudinaryService;

    @Override
    @PostMapping("/uploadPhoto")
    public String uploadImage(@RequestHeader("Authorization") String token, @RequestParam("image") MultipartFile image) throws IOException {
        String imageUrl = restCloudinaryService.uploadImage(image);
        return "Hi Admin. We got this! Take the URL: " + imageUrl;
    }
}
