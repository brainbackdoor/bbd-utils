package com.brainbackdoor.demo.model;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CsvTemplate implements Serializable {
    private Field[] fields;

    public CsvSchema makeSchema() {
        CsvSchema.Builder schemaBuilder = CsvSchema.builder();
        Arrays.stream(fields).forEach(col -> schemaBuilder.addColumn(col.getName()));
        return schemaBuilder.build().withLineSeparator("\r").withHeader();
    }
}
