package uz.pdp.appshortlink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppShortLinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppShortLinkApplication.class, args);
    }



}
