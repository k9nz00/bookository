package ru.semka.bookository.server.util;

import java.time.Clock;
import java.time.ZoneOffset;

public class CommonUtil {
    //TODO значение оффсета вынести в конфиг
    public static ZoneOffset ZONE_OFFSET = ZoneOffset.of("+03");
    public static Clock SYSTEM_CLOCK = Clock.system(ZONE_OFFSET);
}
