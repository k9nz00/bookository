package ru.semka.bookository.server.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("app-config")
@Component
@Data
public class DefaultApplicationProperties {
    private String systemOffsetId;
}
