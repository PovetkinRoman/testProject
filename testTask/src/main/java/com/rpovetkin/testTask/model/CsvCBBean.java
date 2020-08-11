package com.rpovetkin.testTask.model;

import com.opencsv.bean.CsvBindByPosition;

public class CsvCBBean {

    @CsvBindByPosition(position = 0)
    private String agreementNumber;

    @CsvBindByPosition(position = 1)
    private String agreementDate;

    @CsvBindByPosition(position = 2)
    private String agreementId;

    @CsvBindByPosition(position = 3)
    private String dischargeAgreementDate;

    @CsvBindByPosition(position = 4)
    private String dischargeFactDate;

    @CsvBindByPosition(position = 5)
    private String obligationDate;

    @CsvBindByPosition(position = 6)
    private String loanSum;

    @CsvBindByPosition(position = 7)
    private String trancheNumber;

    @CsvBindByPosition(position = 8)
    private String trancheDate;

    @CsvBindByPosition(position = 9)
    private String trancheSum;

    @CsvBindByPosition(position = 10)
    private String agreementType;

    @CsvBindByPosition(position = 11)
    private String loanerType;

    @CsvBindByPosition(position = 12)
    private String personallyIdentifyDoc;

    @CsvBindByPosition(position = 13)
    private String passportSeries;

    @CsvBindByPosition(position = 14)
    private String passportNum;

    @CsvBindByPosition(position = 15)
    private String birthDate;

    @CsvBindByPosition(position = 16)
    private String borrowerSurname;

    @CsvBindByPosition(position = 17)
    private String borrowerName;

    @CsvBindByPosition(position = 18)
    private String borrowerSecondName;

    @CsvBindByPosition(position = 19)
    private String loanerINN;

    @CsvBindByPosition(position = 20)
    private String loanerOGRN;

    @CsvBindByPosition(position = 21)
    private String loanerFullName;

    @CsvBindByPosition(position = 22)
    private String loanerINNLE;

    @CsvBindByPosition(position = 23)
    private String loanerOGRNLE;

    @CsvBindByPosition(position = 24)
    private String srcAgreementNumber;

    @CsvBindByPosition(position = 25)
    private String srcAgreementDate;

    @CsvBindByPosition(position = 26)
    private String srcINN;

    @CsvBindByPosition(position = 27)
    private String srcOGRN;

    @CsvBindByPosition(position = 28)
    private String srcShortName;

    @CsvBindByPosition(position = 29)
    private String messageDateTime;

    @CsvBindByPosition(position = 30)
    private String messageNumber;

    @CsvBindByPosition(position = 31)
    private String ticketDateTime;

    @CsvBindByPosition(position = 32)
    private String ticketNumber;

    public CsvCBBean() {
    }

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    public String getAgreementDate() {
        return agreementDate;
    }

    public void setAgreementDate(String agreementDate) {
        this.agreementDate = agreementDate;
    }

    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
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

    public String getLoanSum() {
        return loanSum;
    }

    public void setLoanSum(String loanSum) {
        this.loanSum = loanSum;
    }

    public String getTrancheNumber() {
        return trancheNumber;
    }

    public void setTrancheNumber(String trancheNumber) {
        this.trancheNumber = trancheNumber;
    }

    public String getTrancheDate() {
        return trancheDate;
    }

    public void setTrancheDate(String trancheDate) {
        this.trancheDate = trancheDate;
    }

    public String getTrancheSum() {
        return trancheSum;
    }

    public void setTrancheSum(String trancheSum) {
        this.trancheSum = trancheSum;
    }

    public String getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(String agreementType) {
        this.agreementType = agreementType;
    }

    public String getLoanerType() {
        return loanerType;
    }

    public void setLoanerType(String loanerType) {
        this.loanerType = loanerType;
    }

    public String getPersonallyIdentifyDoc() {
        return personallyIdentifyDoc;
    }

    public void setPersonallyIdentifyDoc(String personallyIdentifyDoc) {
        this.personallyIdentifyDoc = personallyIdentifyDoc;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
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

    public String getLoanerINN() {
        return loanerINN;
    }

    public void setLoanerINN(String loanerINN) {
        this.loanerINN = loanerINN;
    }

    public String getLoanerOGRN() {
        return loanerOGRN;
    }

    public void setLoanerOGRN(String loanerOGRN) {
        this.loanerOGRN = loanerOGRN;
    }

    public String getLoanerFullName() {
        return loanerFullName;
    }

    public void setLoanerFullName(String loanerFullName) {
        this.loanerFullName = loanerFullName;
    }

    public String getLoanerINNLE() {
        return loanerINNLE;
    }

    public void setLoanerINNLE(String loanerINNLE) {
        this.loanerINNLE = loanerINNLE;
    }

    public String getLoanerOGRNLE() {
        return loanerOGRNLE;
    }

    public void setLoanerOGRNLE(String loanerOGRNLE) {
        this.loanerOGRNLE = loanerOGRNLE;
    }

    public String getSrcAgreementNumber() {
        return srcAgreementNumber;
    }

    public void setSrcAgreementNumber(String srcAgreementNumber) {
        this.srcAgreementNumber = srcAgreementNumber;
    }

    public String getSrcAgreementDate() {
        return srcAgreementDate;
    }

    public void setSrcAgreementDate(String srcAgreementDate) {
        this.srcAgreementDate = srcAgreementDate;
    }

    public String getSrcINN() {
        return srcINN;
    }

    public void setSrcINN(String srcINN) {
        this.srcINN = srcINN;
    }

    public String getSrcOGRN() {
        return srcOGRN;
    }

    public void setSrcOGRN(String srcOGRN) {
        this.srcOGRN = srcOGRN;
    }

    public String getSrcShortName() {
        return srcShortName;
    }

    public void setSrcShortName(String srcShortName) {
        this.srcShortName = srcShortName;
    }

    public String getMessageDateTime() {
        return messageDateTime;
    }

    public void setMessageDateTime(String messageDateTime) {
        this.messageDateTime = messageDateTime;
    }

    public String getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(String messageNumber) {
        this.messageNumber = messageNumber;
    }

    public String getTicketDateTime() {
        return ticketDateTime;
    }

    public void setTicketDateTime(String ticketDateTime) {
        this.ticketDateTime = ticketDateTime;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
}
