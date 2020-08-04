package com.rpovetkin.testTask.model;

import com.opencsv.bean.CsvBindByPosition;

public class CsvSimpleBean {
    @CsvBindByPosition(position = 0)
    private String brand;

    @CsvBindByPosition(position = 1)
    private String agreementNumber;

    @CsvBindByPosition(position = 2)
    private String agreementDate;

    @CsvBindByPosition(position = 3)
    private String agreementId;

    @CsvBindByPosition(position = 4)
    private String loanId;

    @CsvBindByPosition(position = 5)
    private String nameFileToNBCH;

    @CsvBindByPosition(position = 6)
    private String toNBCHDate;

    @CsvBindByPosition(position = 7)
    private String nameFileFromNBCH;

    @CsvBindByPosition(position = 8)
    private String ticketDateTime;

    //------------------------------EQUIFAX BLOCK
    @CsvBindByPosition(position = 9)
    private String codeForEquifax;

    @CsvBindByPosition(position = 10)
    private String agreementNumberForEquifax;

    @CsvBindByPosition(position = 11)
    private String nameFileToEquifax;

    //название файла из системы эквифакса
    @CsvBindByPosition(position = 12)
    private String nameFileOpinionEquifax;

    @CsvBindByPosition(position = 13)
    private String fileEquifaxResponseDate;

    @CsvBindByPosition(position = 14)
    private String fileEquifaxRequestDate;

    @CsvBindByPosition(position = 15)
    private String nameTicketFileFromEquifax;

    @CsvBindByPosition(position = 16)
    private String ticketFileFromEquifaxDate;

    @CsvBindByPosition(position = 17)
    private String dischargeAgreementDate;

    @CsvBindByPosition(position = 18)
    private String dischargeFactDate;

    @CsvBindByPosition(position = 19)
    private String obligationDate;

    @CsvBindByPosition(position = 20)
    private String loanAmount;

    @CsvBindByPosition(position = 21)
    private String passportSeries;

    @CsvBindByPosition(position = 22)
    private String passportNumber;

    @CsvBindByPosition(position = 23)
    private String birthDate;

    @CsvBindByPosition(position = 24)
    private String borrowerSurname;

    @CsvBindByPosition(position = 25)
    private String borrowerName;

    @CsvBindByPosition(position = 26)
    private String borrowerSecondName;


    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getNameFileToNBCH() {
        return nameFileToNBCH;
    }

    public void setNameFileToNBCH(String nameFileToNBCH) {
        this.nameFileToNBCH = nameFileToNBCH;
    }

    public String getToNBCHDate() {
        return toNBCHDate;
    }

    public void setToNBCHDate(String toNBCHDate) {
        this.toNBCHDate = toNBCHDate;
    }

    public String getNameFileFromNBCH() {
        return nameFileFromNBCH;
    }

    public void setNameFileFromNBCH(String nameFileFromNBCH) {
        this.nameFileFromNBCH = nameFileFromNBCH;
    }

    public String getTicketDateTime() {
        return ticketDateTime;
    }

    public void setTicketDateTime(String ticketDateTime) {
        this.ticketDateTime = ticketDateTime;
    }

    public String getCodeForEquifax() {
        return codeForEquifax;
    }

    public void setCodeForEquifax(String codeForEquifax) {
        this.codeForEquifax = codeForEquifax;
    }

    public String getAgreementNumberForEquifax() {
        return agreementNumberForEquifax;
    }

    public void setAgreementNumberForEquifax(String agreementNumberForEquifax) {
        this.agreementNumberForEquifax = agreementNumberForEquifax;
    }

    public String getNameFileToEquifax() {
        return nameFileToEquifax;
    }

    public void setNameFileToEquifax(String nameFileToEquifax) {
        this.nameFileToEquifax = nameFileToEquifax;
    }

    public String getNameFileOpinionEquifax() {
        return nameFileOpinionEquifax;
    }

    public void setNameFileOpinionEquifax(String nameFileOpinionEquifax) {
        this.nameFileOpinionEquifax = nameFileOpinionEquifax;
    }

    public String getFileEquifaxResponseDate() {
        return fileEquifaxResponseDate;
    }

    public void setFileEquifaxResponseDate(String fileEquifaxResponseDate) {
        this.fileEquifaxResponseDate = fileEquifaxResponseDate;
    }

    public String getFileEquifaxRequestDate() {
        return fileEquifaxRequestDate;
    }

    public void setFileEquifaxRequestDate(String fileEquifaxRequestDate) {
        this.fileEquifaxRequestDate = fileEquifaxRequestDate;
    }

    public String getNameTicketFileFromEquifax() {
        return nameTicketFileFromEquifax;
    }

    public void setNameTicketFileFromEquifax(String nameTicketFileFromEquifax) {
        this.nameTicketFileFromEquifax = nameTicketFileFromEquifax;
    }

    public String getTicketFileFromEquifaxDate() {
        return ticketFileFromEquifaxDate;
    }

    public void setTicketFileFromEquifaxDate(String ticketFileFromEquifaxDate) {
        this.ticketFileFromEquifaxDate = ticketFileFromEquifaxDate;
    }

    public String getDischargeAgreementDate() {
        return dischargeAgreementDate;
    }

    public void setDischargeAgreementDate(String dischargeAgreementDate) {
        this.dischargeAgreementDate = dischargeAgreementDate;
    }

    public String getDischargeFactDate() {
        return dischargeFactDate;
    }

    public void setDischargeFactDate(String dischargeFactDate) {
        this.dischargeFactDate = dischargeFactDate;
    }

    public String getObligationDate() {
        return obligationDate;
    }

    public void setObligationDate(String obligationDate) {
        this.obligationDate = obligationDate;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBorrowerSurname() {
        return borrowerSurname;
    }

    public void setBorrowerSurname(String borrowerSurname) {
        this.borrowerSurname = borrowerSurname;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBorrowerSecondName() {
        return borrowerSecondName;
    }

    public void setBorrowerSecondName(String borrowerSecondName) {
        this.borrowerSecondName = borrowerSecondName;
    }

    public String getAgreementDate() {
        return agreementDate;
    }

    public void setAgreementDate(String agreementDate) {
        this.agreementDate = agreementDate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    @Override
    public String toString() {
        return "CsvSimpleBean{" +
                "brand='" + brand + '\'' +
                ", agreementNumber='" + agreementNumber + '\'' +
                '}';
    }
}
