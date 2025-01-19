package ai.az.example.controller;

import ai.az.example.dto.ImageRequest;
import ai.az.example.dto.ResponseType;
import ai.az.example.service.ImageService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {

    Logger log = LoggerFactory.getLogger(ImageController.class);
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/gen-ai-image")
    public void getImageAndRedirectToImageURL(HttpServletResponse response, @RequestParam String prompt, @RequestParam(defaultValue = "hd") String quality,
                                              @RequestParam(defaultValue = "1") int numberOfImages, @RequestParam(defaultValue = "1024") int width,
                                              @RequestParam(defaultValue = "1024") int height,
                                              @RequestParam(defaultValue = "URL") ResponseType responseType) throws IOException {
        log.info("Image controller is running and redirecting to image URL");
        ImageRequest imageRequest = new ImageRequest(prompt, quality, numberOfImages, width, height, responseType);
        ImageResponse imageResponse = imageService.generateImage(imageRequest);
        String imageUrl = imageResponse.getResult().getOutput().getUrl();
        log.info("AI Image generated successfully and URL: {} ", imageUrl);
        response.sendRedirect(imageUrl);
    }

    @GetMapping("/gen-ai-image-url")
    public String getImageURL(@RequestParam String prompt, @RequestParam(defaultValue = "hd") String quality,
                              @RequestParam(defaultValue = "1") int numberOfImages, @RequestParam(defaultValue = "1024") int width,
                              @RequestParam(defaultValue = "1024") int height,
                              @RequestParam(defaultValue = "URL") ResponseType responseType) {
        log.info("Image controller is running");
        ImageRequest imageRequest = new ImageRequest(prompt, quality, numberOfImages, width, height, responseType);
        ImageResponse imageResponse = imageService.generateImage(imageRequest);
        String imageUrl = imageResponse.getResult().getOutput().getUrl();
        log.info("AI Image generated successfully and URL: {} ", imageUrl);
        return imageUrl;
    }
}