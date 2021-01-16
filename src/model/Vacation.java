package model;

import java.util.Objects;

public class Vacation {
    private Integer remainingVacationCurrentYear;
    private Integer consumerVacationCurrentYear;
    private Integer allowedVacationEachMonth;

    public Integer getRemainingVacationCurrentYear() {
        return remainingVacationCurrentYear;
    }

    public void setRemainingVacationCurrentYear(Integer remainingVacationCurrentYear) {
        this.remainingVacationCurrentYear = remainingVacationCurrentYear;
    }

    public Integer getConsumerVacationCurrentYear() {
        return consumerVacationCurrentYear;
    }

    public void setConsumerVacationCurrentYear(Integer consumerVacationCurrentYear) {
        this.consumerVacationCurrentYear = consumerVacationCurrentYear;
    }

    public Integer getAllowedVacationEachMonth() {
        return allowedVacationEachMonth;
    }

    public void setAllowedVacationEachMonth(Integer allowedVacationEachMonth) {
        this.allowedVacationEachMonth = allowedVacationEachMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vacation)) return false;
        Vacation vacation = (Vacation) o;
        return Objects.equals(getRemainingVacationCurrentYear(), vacation.getRemainingVacationCurrentYear()) &&
                Objects.equals(getConsumerVacationCurrentYear(), vacation.getConsumerVacationCurrentYear()) &&
                Objects.equals(getAllowedVacationEachMonth(), vacation.getAllowedVacationEachMonth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRemainingVacationCurrentYear(), getConsumerVacationCurrentYear(), getAllowedVacationEachMonth());
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "remainingVacationCurrentYear=" + remainingVacationCurrentYear +
                ", consumerVacationCurrentYear=" + consumerVacationCurrentYear +
                ", allowedVacationEachMonth=" + allowedVacationEachMonth +
                '}';
    }
}
