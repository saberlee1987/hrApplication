package model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import service.LocalDateDeSerializer;
import service.LocalDateSerializer;

import java.time.LocalDate;
import java.util.Objects;

public class Employee extends Person {
    private String personnelNumber;
    private String accountNumber;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeSerializer.class)
    private LocalDate startWork;
    private Contract contract;
    private Vacation vacation;
    private Long taxExemptSalary;
    private Long baseSalary;
    private Integer score;
    private Integer currentMonthPerformance;

    public Long increaseSalary() {
        if (contract.getType() == ContractType.Contractual) {
            int percent = score / 50;
            return getBaseSalary() * percent;
        }
        return 0L;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long impureSalary(int currentMonthPerformance) {
        return baseSalary + extraSalary(currentMonthPerformance);
    }

    public Long salaryPerHour() {
        return baseSalary / 176;
    }

    public Double pureSalary(int currentMonthPerformance) {
        return (impureSalary(currentMonthPerformance) - diminutionSalary(currentMonthPerformance) - tax() - insurance());
    }

    public Long getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Long baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Long extraSalary(int currentMonthPerformance) {
        if (currentMonthPerformance > 176) {
            int d = currentMonthPerformance - 176;
            if (d > 40) {
                return (Double.valueOf(40 * 1.2 * salaryPerHour()).longValue());
            } else {
                return (Double.valueOf(d * 1.2 * salaryPerHour())).longValue();
            }
        }
        return 0L;
    }

    public Double tax() {
        if (baseSalary <= taxExemptSalary) {
            return 0.0;
        } else {
            return (baseSalary - taxExemptSalary) * 0.1;
        }
    }

    public Double insurance() {
        if (contract.getType() != ContractType.PartTime) {
            return .07 * baseSalary;
        } else {
            return 0.0;
        }
    }

    public Integer getCurrentMonthPerformance() {
        return currentMonthPerformance;
    }

    public void setCurrentMonthPerformance(Integer currentMonthPerformance) {
        this.currentMonthPerformance = currentMonthPerformance;
    }

    public Long diminutionSalary(int currentMonthPerformance) {
        if (currentMonthPerformance < 176) {
            int d = 176 - currentMonthPerformance;
            if (contract.getType() == ContractType.FullTime || contract.getType() == ContractType.Contractual) {
                Vacation vacation = getVacation();
                int v = vacation.getAllowedVacationEachMonth() + vacation.getRemainingVacationCurrentYear();
                if (v == d || v > d) {
                    return 0L;
                } else {
                    return -((Double.valueOf((d - v) * 1.2 * baseSalary))).longValue();
                }
            } else {
                return -(Double.valueOf((d * 1.2 * baseSalary))).longValue();
            }
        } else {
            return 0L;
        }
    }

    public String showPayrollEmployee(int currentMonthPerformance) {
        return String.format("firstName : %s\nlastName : %s\nbirthDate:%s\naccountNumber:%s\ncurrentMonthPerformance=%s" +
                        "\nsalaryPerHour:%s\nimpureSalary=%s\npureSalary=%s\ntax=%s\ninsurance=%s",
                getFirstName(), getLastName(), getBirthDate(), getAccountNumber(), currentMonthPerformance,
                salaryPerHour(), impureSalary(currentMonthPerformance),pureSalary(currentMonthPerformance).longValue(), tax().longValue(), insurance().longValue());
    }


    public String getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(String personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public LocalDate getStartWork() {
        return startWork;
    }

    public void setStartWork(LocalDate startWork) {
        this.startWork = startWork;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Vacation getVacation() {
        return vacation;
    }

    public void setVacation(Vacation vacation) {
        this.vacation = vacation;
    }

    public Long getTaxExemptSalary() {
        return taxExemptSalary;
    }

    public void setTaxExemptSalary(Long taxExemptSalary) {
        this.taxExemptSalary = taxExemptSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getPersonnelNumber(), employee.getPersonnelNumber()) &&
                Objects.equals(getAccountNumber(), employee.getAccountNumber()) &&
                Objects.equals(getStartWork(), employee.getStartWork()) &&
                Objects.equals(getContract(), employee.getContract()) &&
                Objects.equals(getVacation(), employee.getVacation()) &&
                Objects.equals(getTaxExemptSalary(), employee.getTaxExemptSalary()) &&
                Objects.equals(getBaseSalary(), employee.getBaseSalary()) &&
                Objects.equals(getScore(), employee.getScore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPersonnelNumber(), getAccountNumber(), getStartWork(), getContract(), getVacation(), getTaxExemptSalary(), getBaseSalary(), getScore());
    }

    @Override
    public String toString() {
        return "Employee{" +
                super.toString()+
                "personnelNumber='" + personnelNumber + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", startWork=" + startWork +
                ", contract=" + contract +
                ", vacation=" + vacation +
                ", taxExemptSalary=" + taxExemptSalary +
                ", baseSalary=" + baseSalary +
                ", score=" + score +
                '}';
    }
}
