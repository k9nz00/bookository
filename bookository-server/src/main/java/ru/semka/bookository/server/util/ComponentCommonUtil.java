package ru.semka.bookository.server.util;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.semka.bookository.server.common.properties.DefaultApplicationProperties;

import java.time.Clock;
import java.time.ZoneOffset;

@Component
@Data
public class ComponentCommonUtil {
    public final DefaultApplicationProperties properties;
    private final ZoneOffset zoneOffset;
    private final Clock systemClock;

    public ComponentCommonUtil(DefaultApplicationProperties properties) {
        this.properties = properties;
        zoneOffset = ZoneOffset.of(properties.getSystemOffsetId());
        systemClock = Clock.system(zoneOffset);
    }
}
