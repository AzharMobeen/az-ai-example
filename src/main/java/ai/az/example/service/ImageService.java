package ai.az.example.service;

import ai.az.example.dto.ImageRequest;
import ai.az.example.dto.ResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    Logger log = LoggerFactory.getLogger(ImageService.class);

    private final OpenAiImageModel openAiImageModel;

    public ImageService(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    public ImageResponse generateImage(ImageRequest request) {
        log.info("Image service is running with request: {}", request);
        validateRequest(request);
        String model = request.numberOfImages() > 1 ? "dall-e-2" : "dall-e-3";
        String responseFormat = request.responseType().name();
        OpenAiImageOptions options = OpenAiImageOptions.builder()
                .withQuality(request.quality())
                .withModel(model)
                .withResponseFormat(responseFormat.toLowerCase())
                .withN(request.numberOfImages())
                .withWidth(request.width())
                .withHeight(request.height())
                .build();
        ImagePrompt imagePrompt = new ImagePrompt(request.prompt(), options);
        return openAiImageModel.call(imagePrompt);
    }

    private void validateRequest(ImageRequest imageRequest) {
        // Add validation logic here
        List<ResponseType> validResponseTypes = List.of(ResponseType.URL, ResponseType.B64_JSON);
        List<String> validDallE2WidthAndHeight = List.of("256x256", "512x256", "1024x1024");
        List<String> validDallE3WidthAndHeight = List.of("1024x1024", "1792x1024", "1024x1792");
        if (!validResponseTypes.contains(imageRequest.responseType())) {
            throw new IllegalArgumentException("Invalid response type");
        }
        String requestedWidthAndHeight = imageRequest.width() + "x" + imageRequest.height();
        if(imageRequest.numberOfImages() > 1) {
            if(!validDallE2WidthAndHeight.contains(requestedWidthAndHeight)) {
                throw new IllegalArgumentException("Invalid width and height for DALL-E 2 model");
            }
        } else {
            if(!validDallE3WidthAndHeight.contains(requestedWidthAndHeight)) {
                throw new IllegalArgumentException("Invalid width and height for DALL-E 3 model");
            }
        }
    }
}
