package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import service.LocalDateDeSerializer;
import service.LocalDateSerializer;

import java.time.LocalDate;
import java.util.Objects;

public class Contract {
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeSerializer.class)
    private LocalDate startContractDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeSerializer.class)
    private LocalDate endContractDate;
    private ContractType type;

    public LocalDate getStartContractDate() {
        return startContractDate;
    }

    public void setStartContractDate(LocalDate startContractDate) {
        this.startContractDate = startContractDate;
    }

    public LocalDate getEndContractDate() {
        return endContractDate;
    }

    public void setEndContractDate(LocalDate endContractDate) {
        this.endContractDate = endContractDate;
    }

    public ContractType getType() {
        return type;
    }

    public void setType(ContractType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contract)) return false;
        Contract contract = (Contract) o;
        return Objects.equals(getStartContractDate(), contract.getStartContractDate()) &&
                Objects.equals(getEndContractDate(), contract.getEndContractDate()) &&
                getType() == contract.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartContractDate(), getEndContractDate(), getType());
    }

    @Override
    public String toString() {
        return "Contract{" +
                "startContractDate=" + startContractDate +
                ", endContractDate=" + endContractDate +
                ", type=" + type +
                '}';
    }
}
