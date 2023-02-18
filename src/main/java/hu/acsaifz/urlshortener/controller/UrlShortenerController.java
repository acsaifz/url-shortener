package hu.acsaifz.urlshortener.controller;

import hu.acsaifz.urlshortener.model.dto.ShortUrlCreateRequest;
import hu.acsaifz.urlshortener.model.dto.UrlDto;
import hu.acsaifz.urlshortener.service.UrlShortenerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/short-url")
public class UrlShortenerController {
    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping
    public UrlDto createShortUrl(@RequestBody ShortUrlCreateRequest shortUrlCreateRequest){
        return urlShortenerService.createShortUrl(shortUrlCreateRequest);
    }

    @GetMapping("/{shortUrl}")
    public UrlDto findUrl(@PathVariable String shortUrl){
        return urlShortenerService.findUrlByShortUrl(shortUrl);
    }

}
