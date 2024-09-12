package ru.semka.bookository.server.util;

import org.junit.jupiter.api.Test;
import ru.semka.bookository.server.configuration.properties.DefaultApplicationProperties;

import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ComponentCommonUtilTest {

    @Test
    void getZoneOffset() {
        DefaultApplicationProperties properties = mock(DefaultApplicationProperties.class);
        when(properties.getSystemOffsetId()).thenReturn("+03");

        ComponentCommonUtil commonUtil = new ComponentCommonUtil(properties);

        ZoneOffset zoneOffset = commonUtil.getZoneOffset();
        assertEquals("+03:00", zoneOffset.getId());
    }

    @Test
    void getSystemClock() {
        DefaultApplicationProperties properties = mock(DefaultApplicationProperties.class);
        when(properties.getSystemOffsetId()).thenReturn("+03");

        ComponentCommonUtil commonUtil = new ComponentCommonUtil(properties);
        assertNotNull(commonUtil.getSystemClock());
    }
}