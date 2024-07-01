package uz.pdp.appshortlink.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final JWT jwt = new JWT();

    @Getter
    @Setter
    public static class JWT{
        private String secretKey;
        private Integer expireDays;
    }

}
