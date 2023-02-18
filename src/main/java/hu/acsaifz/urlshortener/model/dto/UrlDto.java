package hu.acsaifz.urlshortener.model.dto;

public record UrlDto(
        String shortUrl,
        String originalUrl
) {
}
