package com.rpovetkin.testTask.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Data
@Getter
@Setter
@ToString
public class DebtRatio {

    Long id;

    Date calcDate;

    Long loanId;

    Long nbkiHistoryId;

    Long equifaxHistoryId;

    String bureauType;

    BigDecimal pdn;

    BigDecimal k1;

    BigDecimal k2New;

    BigDecimal k2Current;

    BigDecimal k3;

    public DebtRatio() {
    }

    public DebtRatio(MultiValueMap<String, String> data) {
        this.calcDate = new Date();
        this.loanId = Long.valueOf(Objects.requireNonNull(data.getFirst("loanId")));
        this.nbkiHistoryId = Long.valueOf(Objects.requireNonNull(data.getFirst("nbkiHistoryId")));
        this.equifaxHistoryId = Long.valueOf(Objects.requireNonNull(data.getFirst("equifaxHistoryId")));
        this.bureauType = data.getFirst("bureauType");
        this.pdn = new BigDecimal(Objects.requireNonNull(data.getFirst("pdn")));
        this.k1 = new BigDecimal(Objects.requireNonNull(data.getFirst("k1")));
        this.k2New = new BigDecimal(Objects.requireNonNull(data.getFirst("k2New")));
        this.k2Current = new BigDecimal(Objects.requireNonNull(data.getFirst("k2Current")));
        this.k3 = new BigDecimal(Objects.requireNonNull(data.getFirst("k3")));;
    }
}
