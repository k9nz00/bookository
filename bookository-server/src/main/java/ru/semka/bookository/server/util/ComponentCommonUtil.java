package ru.semka.bookository.server.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.semka.bookository.server.configuration.properties.DefaultApplicationProperties;

import java.time.Clock;
import java.time.ZoneOffset;

@Component
@Getter
public class ComponentCommonUtil {
    private final ZoneOffset zoneOffset;
    private final Clock systemClock;

    @Autowired
    public ComponentCommonUtil(DefaultApplicationProperties properties) {
        zoneOffset = ZoneOffset.of(properties.getSystemOffsetId());
        systemClock = Clock.system(zoneOffset);
    }
}
