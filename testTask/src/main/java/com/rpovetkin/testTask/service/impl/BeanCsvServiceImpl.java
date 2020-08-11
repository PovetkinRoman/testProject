package com.rpovetkin.testTask.service.impl;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.*;
import com.rpovetkin.testTask.model.CsvCBBean;
import com.rpovetkin.testTask.model.CsvSimpleBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class BeanCsvServiceImpl {

    public final static String FIN4_NBCH_AGREEMENT_NUMBER = "270215/4ФИНАНС-И";
    public final static String FIN4_NBCH_AGREEMENT_DATE = "27.02.2015";
    public final static String FIN4_EQUIFAX_AGREEMENT_NUMBER = "б/н";
    public final static String FIN4_EQUIFAX_AGREEMENT_DATE = "29.07.2012";

    public final static String SMS_NBCH_AGREEMENT_NUMBER = "160812/Г-МСК-И";
    public final static String SMS_NBCH_AGREEMENT_DATE = "16.08.12";
    public final static String SMS_EQUIFAX_AGREEMENT_NUMBER = "ZZV070815-1";
    public final static String SMS_EQUIFAX_AGREEMENT_DATE = "07.08.2015";

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<CsvSimpleBean> beanBuilderExample(Path path, Class clazz) {
        List<CsvSimpleBean> resultList = new ArrayList<>();
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

            int count = 0;
            for (CsvSimpleBean csvSimpleBean : (Iterable<CsvSimpleBean>) cb) {
                System.out.println("AgreementNumber : " + csvSimpleBean.getAgreementNumber());
                System.out.println("Brand : " + csvSimpleBean.getBrand());
                System.out.println("SecondName : " + csvSimpleBean.getBorrowerSecondName());
                System.out.println("Name : " + csvSimpleBean.getBorrowerName());
                System.out.println("LoanAmt : " + csvSimpleBean.getLoanAmount());
                System.out.println("AgreementId : " + csvSimpleBean.getAgreementId());
                System.out.println("Count : " + count);
                System.out.println("==========================");
                count++;
                resultList.add(csvSimpleBean);
            }

//            resultList = cb.parse();
            reader.close();
            return resultList;
        } catch (Exception ex) {
            log.error("exception in builder bean");
            ex.printStackTrace();
            return null;
        }
    }

    public void writeCsvFromBean(List<?> dataForCsvList) throws Exception {
        Writer writer = new FileWriter("C:\\Projects\\testProject\\testTask\\src\\main\\resources\\csv\\testWriter.csv");
        log.info("csvFileWriter inside");
        StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                .withSeparator(';')
//                .withApplyQuotesToAll(false)
                .build();
        sbc.write(dataForCsvList);
        writer.close();
    }

    public void mappingCsvSimpleBeanToCsvResultBean(List<CsvSimpleBean> csvSimpleBeanList) {
        List<CsvCBBean> csvCBBeans = new ArrayList<>();
        try {
            for (CsvSimpleBean csvSimpleBean : csvSimpleBeanList) {
                CsvCBBean resultBean = new CsvCBBean();
                resultBean.setAgreementNumber(csvSimpleBean.getAgreementNumber());
                resultBean.setAgreementDate(csvSimpleBean.getAgreementDate());
                resultBean.setAgreementId(csvSimpleBean.getAgreementId());
                resultBean.setDischargeAgreementDate(csvSimpleBean.getDischargeAgreementDate());
                resultBean.setDischargeFactDate(csvSimpleBean.getDischargeFactDate());
                resultBean.setObligationDate(csvSimpleBean.getObligationDate());
                resultBean.setLoanSum(csvSimpleBean.getLoanAmount());
                resultBean.setTrancheNumber("1");
                resultBean.setTrancheDate(csvSimpleBean.getObligationDate());
                resultBean.setTrancheSum(csvSimpleBean.getLoanAmount());
                resultBean.setAgreementType("Микрозайм (микрокредит)");
                resultBean.setLoanerType("ФЛ");
                resultBean.setPersonallyIdentifyDoc("1");
                resultBean.setPassportSeries(csvSimpleBean.getPassportSeries());
                resultBean.setPassportNum(csvSimpleBean.getPassportNumber());
                resultBean.setBirthDate(csvSimpleBean.getBirthDate());
                resultBean.setBorrowerSurname(csvSimpleBean.getBorrowerSurname());
                resultBean.setBorrowerName(csvSimpleBean.getBorrowerName());
                resultBean.setBorrowerSecondName(csvSimpleBean.getBorrowerSecondName());
                resultBean.setLoanerINN("");
                resultBean.setLoanerOGRN("");
                resultBean.setLoanerFullName("");
                resultBean.setLoanerINNLE("");
                resultBean.setLoanerINNLE("");
                resultBean.setLoanerOGRNLE("");
                resultBean.setSrcINN("7703548386");
                resultBean.setSrcOGRN("1057746710713");
                resultBean.setSrcShortName("АО НБКИ");
                resultBean.setMessageDateTime(csvSimpleBean.getToNBCHDate());
                resultBean.setMessageNumber(csvSimpleBean.getNameFileToNBCH());
                resultBean.setTicketDateTime(csvSimpleBean.getTicketDateTime());
                resultBean.setTicketNumber(csvSimpleBean.getNameFileFromNBCH());
                String agreementNumber = csvSimpleBean.getBrand().contains("SMS")
                        ? SMS_NBCH_AGREEMENT_NUMBER
                        : FIN4_NBCH_AGREEMENT_NUMBER;
                String agreementDate = csvSimpleBean.getBrand().contains("SMS")
                        ? SMS_NBCH_AGREEMENT_DATE
                        : FIN4_NBCH_AGREEMENT_DATE;
                resultBean.setSrcAgreementNumber(agreementNumber);
                resultBean.setSrcAgreementDate(agreementDate);
                csvCBBeans.add(resultBean);
                if(!csvSimpleBean.getNameFileToEquifax().isEmpty()) {
                    CsvCBBean csvEquifaxBean = prepareEquifaxBean(csvSimpleBean);
                    csvCBBeans.add(csvEquifaxBean);
                }
            }
            this.writeCsvFromBean(csvCBBeans);
        } catch (Exception ex) {
            log.error("mappingCsvSimpleBeanToCsvResultBean");
            ex.printStackTrace();
        }
    }

    private CsvCBBean prepareEquifaxBean(CsvSimpleBean csvSimpleBean) {
        CsvCBBean result = new CsvCBBean();
        result.setAgreementNumber(csvSimpleBean.getAgreementNumberForEquifax());
        result.setAgreementDate(csvSimpleBean.getAgreementDate());
        result.setAgreementId(csvSimpleBean.getAgreementId());
        result.setDischargeAgreementDate(csvSimpleBean.getDischargeAgreementDate());
        result.setDischargeFactDate(csvSimpleBean.getDischargeFactDate());
        result.setObligationDate(csvSimpleBean.getObligationDate());
        result.setLoanSum(csvSimpleBean.getLoanAmount());
        result.setTrancheNumber("1");
        result.setTrancheDate(csvSimpleBean.getObligationDate());
        result.setTrancheSum(csvSimpleBean.getLoanAmount());
        result.setAgreementType("Микрозайм (микрокредит)");
        result.setLoanerType("ФЛ");
        result.setPersonallyIdentifyDoc("1");
        result.setPassportSeries(csvSimpleBean.getPassportSeries());
        result.setPassportNum(csvSimpleBean.getPassportNumber());
        result.setBirthDate(csvSimpleBean.getBirthDate());
        result.setBorrowerSurname(csvSimpleBean.getBorrowerSurname());
        result.setBorrowerName(csvSimpleBean.getBorrowerName());
        result.setBorrowerSecondName(csvSimpleBean.getBorrowerSecondName());
        result.setLoanerINN("");
        result.setLoanerOGRN("");
        result.setLoanerFullName("");
        result.setLoanerINNLE("");
        result.setLoanerINNLE("");
        result.setLoanerOGRNLE("");
        result.setSrcINN("7813199667");
        result.setSrcOGRN("1047820008895");
        result.setSrcShortName("ООО ЭКС");
        result.setMessageDateTime(csvSimpleBean.getFileEquifaxRequestDate());
        result.setMessageNumber(csvSimpleBean.getNameFileToEquifax());
        result.setTicketDateTime(csvSimpleBean.getTicketFileFromEquifaxDate());
        result.setTicketNumber(csvSimpleBean.getNameFileOpinionEquifax());

        String agreementNumber = csvSimpleBean.getBrand().contains("SMS")
                ? SMS_EQUIFAX_AGREEMENT_NUMBER
                : FIN4_EQUIFAX_AGREEMENT_NUMBER;
        String agreementDate = csvSimpleBean.getBrand().contains("SMS")
                ? SMS_EQUIFAX_AGREEMENT_DATE
                : FIN4_EQUIFAX_AGREEMENT_DATE;

        result.setSrcAgreementNumber(agreementNumber);
        result.setSrcAgreementDate(agreementDate);

        return result;
    }
}
