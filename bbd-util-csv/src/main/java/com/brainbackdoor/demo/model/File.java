package com.brainbackdoor.demo.model;

import com.brainbackdoor.demo.exception.NoEntityFoundException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class File {
    private String path;
    private String name;

    public void write(CsvSchema schema) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + name)));
        new CsvMapper().writer(schema).writeValues(writer).write(schema.getNullValueOrEmpty());
        writer.close();
    }

    public void write(CsvSchema schema, List<Map> cells) throws IOException {
        isValid(cells);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + name), "euc-kr"));
        new CsvMapper().writer(schema).writeValues(writer).writeAll(cells);
        writer.close();
    }

    public List<?> read(Class<?> type) throws IOException {
        return new CsvMapper().readerWithSchemaFor(type)
                .with(CsvSchema.emptySchema().withHeader())
                .readValues(new BufferedReader(new InputStreamReader(new FileInputStream(path + name), "euc-kr")))
                .readAll();
    }

    private void isValid(List<Map> cells) {
        cells.stream().findFirst().orElseThrow(() -> new NoEntityFoundException("There is no data in CSV."));
    }
}
