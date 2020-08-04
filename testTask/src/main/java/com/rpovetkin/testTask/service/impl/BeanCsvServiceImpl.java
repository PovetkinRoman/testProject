package com.rpovetkin.testTask.service.impl;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.*;
import com.rpovetkin.testTask.model.CsvSimpleBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@Slf4j
public class BeanCsvServiceImpl {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<CsvSimpleBean> beanBuilderExample(Path path, Class clazz) {
        List<CsvSimpleBean> resultList;
        ColumnPositionMappingStrategy ms = new ColumnPositionMappingStrategy();
        ms.setType(clazz);
        try {

            Reader reader = Files.newBufferedReader(path);
            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(';')
                    .build();

            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withCSVParser(parser)
                    .build();

            CsvToBean cb = new CsvToBeanBuilder(csvReader)
                    .withType(clazz)
                    .withMappingStrategy(ms)
                    .build();
            resultList = cb.parse();
            reader.close();
            return resultList;
        } catch (Exception ex) {
            log.error("exception in builder bean");
            ex.printStackTrace();
            return null;
        }
    }

    public void writeCsvFromBean(List<CsvSimpleBean> csvSimpleBeansList) throws Exception {
        Writer writer = new FileWriter("C:\\Projects\\testProject\\testTask\\src\\main\\resources\\csv\\testWriter.csv");
        log.info("csvFileWriter inside");
        StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                .withSeparator(';')
                .withApplyQuotesToAll(false)
                .build();
        sbc.write(csvSimpleBeansList);
        writer.close();
    }
}
