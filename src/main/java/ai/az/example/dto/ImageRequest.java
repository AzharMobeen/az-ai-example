package ai.az.example.dto;


// This class is a data transfer object (DTO) that represents the request for an image.
public record ImageRequest(String prompt, String quality, int numberOfImages, int width, int height, ResponseType responseType) {
}
