package hu.acsaifz.urlshortener.repository;

import hu.acsaifz.urlshortener.model.Url;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UrlRepository {
    List<Url> urlList = new ArrayList<>();

    public boolean existsShortUrl(String shortUrl) {
        return urlList.stream().anyMatch(url -> url.getShortUrl().equals(shortUrl));
    }

    public void save(Url url){
        urlList.add(url);
    }

    public Optional<Url> findUrlByShortUrl(String shortUrl){
        return urlList.stream()
                .filter(url -> url.getShortUrl().equals(shortUrl))
                .findFirst();
    }
}
