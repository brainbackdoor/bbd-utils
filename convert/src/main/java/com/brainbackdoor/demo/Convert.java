package com.brainbackdoor.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Convert {
    public static Map stringToMap(String map) throws IOException {
        return new ObjectMapper().readValue(map, Map.class);
    }

    public static String mapToString(Map map) throws JsonProcessingException {
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(map);
    }

    public static JsonNode listToJson(List list) throws IOException {
        return new ObjectMapper().readTree(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(list));
    }

    public static Set<String> stringArrayToSet(String[] array) {
        return new HashSet(Arrays.asList(array));
    }

    public static Map setToMapKey(Set<String> set) {
        return set.stream().collect(Collectors.toMap(Function.identity(), String::new));
    }
}
