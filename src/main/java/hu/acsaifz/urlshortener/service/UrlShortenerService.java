package hu.acsaifz.urlshortener.service;

import hu.acsaifz.urlshortener.exception.ShortUrlNotFoundException;
import hu.acsaifz.urlshortener.model.Url;
import hu.acsaifz.urlshortener.model.dto.ShortUrlCreateRequest;
import hu.acsaifz.urlshortener.model.dto.UrlDto;
import hu.acsaifz.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UrlShortenerService {
    private static final String[] CHARACTERS = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y","z","0","1","2","3","4","5","6","7","8","9"};
    private static final int SHORT_URL_LENGTH = 6;
    private static final String URL_BASE = "http://letsco.de/";
    private final UrlRepository urlRepository;

    public UrlShortenerService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlDto createShortUrl(ShortUrlCreateRequest shortUrlCreateRequest){
        String originalUrl = shortUrlCreateRequest.originalUrl();
        String shortUrl;

        do {
            //shortUrl = generateShortUrl();
            shortUrl = "aaaaab";
        } while(urlRepository.existsShortUrl(shortUrl));

        Url url = new Url(shortUrl, originalUrl);
        urlRepository.save(url);

        return new UrlDto(URL_BASE + url.getShortUrl(), url.getOriginalUrl());
    }

    public UrlDto findUrlByShortUrl(String shortUrl){
        Url url = urlRepository.findUrlByShortUrl(shortUrl)
                .orElseThrow(() -> new ShortUrlNotFoundException("Short url not found: " + shortUrl));

        return new UrlDto(URL_BASE + url.getShortUrl(), url.getOriginalUrl());
    }

    private String generateShortUrl(){
        SecureRandom random = new SecureRandom();
        StringBuilder uuid = new StringBuilder();

        for (int i = 0; i < SHORT_URL_LENGTH; i++){
            uuid.append(CHARACTERS[random.nextInt(CHARACTERS.length)]);
        }

        return uuid.toString();
    }
}
