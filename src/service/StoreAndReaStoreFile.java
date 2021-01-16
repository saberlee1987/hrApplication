package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.objects.NativeJSON;
import model.Employee;
import model.EmployeeDataModel;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class StoreAndReaStoreFile {

    public boolean writeEmployeeToFile(List<Employee> employees, Path path) {
        ObjectMapper objectMapper = new ObjectMapper();
        EmployeeDataModel employeeDataModel = new EmployeeDataModel();
        employeeDataModel.setEmployees(employees);
        try {
            String data = objectMapper.writeValueAsString(employeeDataModel);

            Files.write(path, data.getBytes(),
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Error =====" + ex.getMessage());
            return false;
        }

    }

    public EmployeeDataModel readDataFromFile(Path path) {
        try {
            StringBuilder data = new StringBuilder();
            Files.lines(path).forEach(data::append);
            ObjectMapper objectMapper = new ObjectMapper();
            EmployeeDataModel dataModel = objectMapper.readValue(data.toString(), EmployeeDataModel.class);
            System.out.println(dataModel);
            return dataModel;
        } catch (Exception ex) {

            System.err.println("Error =====>" + ex.getMessage());
            return null;
        }
    }
}
