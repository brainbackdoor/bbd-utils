package com.brainbackdoor.demo.model;

import com.brainbackdoor.demo.exception.NoEntityFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileTest {
    private static final Logger log = LoggerFactory.getLogger(FileTest.class);

    private String fileName = "sample1.csv";
    private String savePath = "C:\\Users\\Echo\\Desktop\\";

    File file = new File(savePath, fileName);

    @Test
    public void WriteCsvTemplateTest() throws IOException {
        file.write(new CsvTemplate(Temp.class.getDeclaredFields()).makeSchema());
    }

    @Test
    public void WriteCsvDataTest() throws IOException {
        Map cells = new HashMap();
        cells.put("temp1", "data1");
        cells.put("temp2", "data2");
        List rows = new ArrayList();
        rows.add(cells);
        file.write(new CsvTemplate(Temp.class.getDeclaredFields()).makeSchema(), rows);
    }

    @Test
    public void LoadCsvFileTest() throws IOException {
        log.debug("{}", file.read(Temp.class));
    }

    @Test(expected = NoEntityFoundException.class)
    public void EmptyFileVerificationTest() throws IOException {
        file.write(new CsvTemplate(Temp.class.getDeclaredFields()).makeSchema(), new ArrayList());
    }
}

class Temp {
    String temp1;
    String temp2;

    public String getTemp1() {
        return temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    @Override
    public String toString() {
        return "Temp{" +
                "temp1='" + temp1 + '\'' +
                ", temp2='" + temp2 + '\'' +
                '}';
    }
}