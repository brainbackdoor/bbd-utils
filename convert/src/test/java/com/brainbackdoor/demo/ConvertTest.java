package com.brainbackdoor.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ConvertTest {
    private static final Logger log = LoggerFactory.getLogger(ConvertTest.class);

    @Test
    public void stringToMapTest() throws IOException {
        String data =
                        "    {\n" +
                        "        \"source\": \"daum\",\n" +
                        "        \"medium\": \"d/a\"\n" +
                        "    }";
        Map map = Convert.stringToMap(data);
        assertEquals("daum", map.get("source"));
    }

    @Test
    public void mapToStringTest() throws JsonProcessingException {
        Map map = new HashMap();
        map.put("source", "daum");
        map.put("medium", "d/a");
        log.debug("{}", Convert.mapToString(map));
    }

    @Test
    public void listMapToJsonTest() throws IOException {
        List<Map<String, Object>> list = new ArrayList();
        Map map = new HashMap();
        map.put("source", "daum");
        map.put("medium", "d/a");
        list.add(map);
        map = new HashMap();
        map.put("source", "naver");
        map.put("medium", "s/a");
        list.add(map);
        log.debug("{}", Convert.listToJson(list));
    }

    @Test
    public void stringArrayToMap() {
        String[] array = {"source", "medium", "campaign", "group", "term"};
        Map map = Convert.setToMapKey(Convert.stringArrayToSet(array));
        map.put("source", "daum");
        assertEquals("daum", map.get("source"));
    }


}