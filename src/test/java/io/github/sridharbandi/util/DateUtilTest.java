package io.github.sridharbandi.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

class DateUtilTest {

    @Test
    void testGetDate() {
        String result = DateUtil.getDate();
        Assertions.assertTrue(result.startsWith(new SimpleDateFormat("dd MMM yyyy").format(new Date())));
    }
}
