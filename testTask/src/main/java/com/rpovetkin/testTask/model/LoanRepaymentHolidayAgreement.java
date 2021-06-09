package com.rpovetkin.testTask.model;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Сущность соглашения клиента на "Кредитные каникулы"
 */
@Data
public class LoanRepaymentHolidayAgreement implements Serializable {

    private Long id;

    /**
     * Используется для быстрого поиска соглашений по клиенту
     */
    private Long clientId;

    /**
     * Каждое соглашение относится к займу
     */
    private Long loanId;

    /**
     * Дата создания соглашения. Создается при получении входящей оплаты, либо из личного кабинета, либо из учетной системы клиента
     */
    private Date createDate;

    /**
     * Код для получения согласия. Аналог собственноручной подписи
     */
    private String confirmationCode;

    /**
     * Код для расторжения соглашения. Аналог собственноручной подписи
     */
    private String dissolveCode;

    /**
     * Дата получения согласия от клиента с условиями соглашения. С этого момента соглашение считается активным.
     */
    private Date confirmationDate;

    /**
     * Ставка на перод действия "Кредитных каникул"
     */
    private BigDecimal actionStake;

    /**
     * Актальная ставка по займа вне периода "Кредитные каникулы"
     */
    private BigDecimal actualStake;

    /**
     * Длительность периода действия на момент создания соглашения. Может не совпадать с количеством дней от {@code periodStartDate} до {@code periodEndDate}
     */
    private int periodDuration;

    /**
     * Дата начала действия периода действия соглашения. С этого момента можно выполнять продление
     */
    private Date periodOpenDate;

    /**
     * Дата окончания периода действия соглашения. С этого момента продление выполнять нельзя.
     */
    private Date periodCloseDate;


    /**
     * Дата окончания действия соглашения. Устанавливается при закрытии соглашения. Фактическая дата установки {@code closeReason}
     */
    private Date closeDate;


    /**
     * Причина закрытия/окончания действия соглашения.
     */
    private String closeReason;

    /**
     * Дата отправки кода для подписи соглашеня. Устанавливается при создании соглашения.
     */
    @Column
    private Date codeSentDate;

    public LoanRepaymentHolidayAgreement() {
    }


    /**
     * Проверка получения согласия
     *
     * @return true - согласие было получено, false - согласие не получено
     */
    public boolean isConfirmed() {
        return confirmationDate != null;
    }

    /**
     * Проверка состояния соглашения: активно
     * Соглашение активно если:
     * - получено согласие
     * - соглашение не закрыто
     *
     * @return true - соглашение активно и может быть использовано как основание для продления либо закрыто, false - соглашение не активно и не может использоваться для продления
     */
    public boolean isActive() {
        return isConfirmed() && !isClosed();
    }

    /**
     * Проверка состояния соглашения: закрыто
     *
     * @return true - если соглашение закрыто, иначе false
     */
    public boolean isClosed() {
        return closeDate != null;
    }

    /**
     * Подтвердить соглашение. Устанавливается дата получения согласия о заключении соглашения {@code confirmationDate}
     */
    public LoanRepaymentHolidayAgreement confirm() {
        confirmationDate = new Date();
        return this;
    }

    @Override
    public String toString() {
        return "LoanRepaymentHolidayAgreement{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", loanId=" + loanId +
                ", createDate=" + createDate +
                ", confirmationCode='" + confirmationCode + '\'' +
                ", dissolveCode='" + dissolveCode + '\'' +
                ", confirmationDate=" + confirmationDate +
                ", actionStake=" + actionStake +
                ", actualStake=" + actualStake +
                ", periodDuration=" + periodDuration +
                ", periodOpenDate=" + periodOpenDate +
                ", periodCloseDate=" + periodCloseDate +
                ", closeDate=" + closeDate +
                ", closeReason=" + closeReason +
                ", codeSentDate=" + codeSentDate +
                '}';
    }
}
