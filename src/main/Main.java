package main;

import model.*;
import service.StoreAndReaStoreFile;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Employee> employees = getEmployeesFromUser();

        for (Employee employee : employees) {
            System.out.println(employee.showPayrollEmployee(employee.getCurrentMonthPerformance()));
            System.out.println("increaseSalary() ===>"+employee.increaseSalary());
            System.out.println("=======================================================");
        }

        EmployeeDataModel employeeDataModel = new EmployeeDataModel();
        employeeDataModel.setEmployees(employees);

        StoreAndReaStoreFile storeAndReaStoreFile = new StoreAndReaStoreFile();

        System.out.println(sumOfTaxAndInsurance(employees));
        System.out.println("===============================================================");
        LocalDateTime dateTime = LocalDateTime.now();
        String timeStore = dateTime.getYear() + "-" + dateTime.getMonth().toString() + "-" + dateTime.getDayOfMonth() + "T"
                + dateTime.getHour() + "-" + dateTime.getMinute() + "-" + dateTime.getSecond();
        String pathName = "data/employee" + timeStore + ".json";
        boolean isStore = storeAndReaStoreFile.writeEmployeeToFile(employees, Paths.get(pathName));
        if (isStore) {
            System.out.println("Your Data is Saved SuccessFully");
        } else {
            System.out.println("Sorry Can not Save Data To File");
        }
        EmployeeDataModel dataModel = storeAndReaStoreFile.readDataFromFile(Paths.get(pathName));
        System.out.println(dataModel);
    }


    private static List<Employee> getEmployeesFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a count number of employee : ");
        int count = scanner.nextInt();
        System.out.println("=============================================================================");
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Employee employee = new Employee();
            System.out.print("Enter firstName Employee  : ");
            employee.setFirstName(scanner.next());
            System.out.print("Enter lastName Employee : ");
            employee.setLastName(scanner.next());
            System.out.print("Enter birthDate (yyyy-mm-dd) : ");
            employee.setBirthDate(LocalDate.parse(scanner.next()));
            System.out.print("Enter employee personnelNumber :  ");
            employee.setPersonnelNumber(scanner.next());
            System.out.print("Enter employee accountNumber : ");
            employee.setAccountNumber(scanner.next());
            System.out.print("Enter employee startWork (yyyy-mm-dd)  : ");
            employee.setStartWork(LocalDate.parse(scanner.next()));

            System.out.println("Enter contract Date for employee : ");
            Contract contract = new Contract();
            System.out.print("Enter start Date Contract (yyyy-mm-dd) : ");

            contract.setStartContractDate(LocalDate.parse(scanner.next()));
            System.out.print("Enter end Date Contract (yyyy-mm-dd) : ");
            contract.setEndContractDate(LocalDate.parse(scanner.next()));
            System.out.print("Enter employee Type ( FullTime or 0, PartTime or 1 , Contractual or 2) : ");
            contract.setType(ContractType.getInstance(scanner.nextInt()));


            employee.setContract(contract);

            Vacation vacation = new Vacation();
            System.out.println("Enter vacation employee information : ");
            System.out.print("Enter remainingVacationCurrentYear (int) for employee : ");
            vacation.setRemainingVacationCurrentYear(scanner.nextInt());
            System.out.print("Enter consumerVacationCurrentYear (int) for employee : ");
            vacation.setConsumerVacationCurrentYear(scanner.nextInt());
            System.out.print("Enter allowedVacationEachMonth (int) for employee : ");
            vacation.setAllowedVacationEachMonth(scanner.nextInt());

            employee.setVacation(vacation);

            System.out.print("Enter taxExemptSalary (int) for employee :  ");
            employee.setTaxExemptSalary(scanner.nextLong());

            System.out.print("Enter baseSalary (int) for employee :  ");
            employee.setBaseSalary(scanner.nextLong());

            System.out.print("Enter score (int) for type of Pemany employee (Contractual) :  ");

            employee.setScore(scanner.nextInt());

            System.out.print("Enter currentMonthPerformance (int) for employee : ");
            employee.setCurrentMonthPerformance(scanner.nextInt());

            employees.add(employee);
            System.out.println("==============================================================================================");

        }
        System.out.println("==============================================================================================");
        return employees;
    }

    private static String sumOfTaxAndInsurance(List<Employee> employees) {
        StringBuilder response = new StringBuilder("{").append("\n");

        Double sumOfTax = 0.0;
        Double sumOfInsurance = 0.0;
        for (Employee employee : employees) {
            sumOfTax += employee.tax();
            sumOfInsurance += employee.insurance();
        }
        response.append("sumOfTax : ").append(sumOfTax.longValue()).append(" , ").append("\n");
        response.append("sumOfInsurance : ").append(sumOfInsurance.longValue()).append("\n");
        response.append("}");
        return response.toString();
    }
}
