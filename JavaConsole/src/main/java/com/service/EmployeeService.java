package com.service;

import com.exceptions.DuplicateException;
import com.exceptions.InvalidFormatException;
import com.exceptions.MinusSalaryException;
import com.model.Employee;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();
    private final String CSV_PATH = "JavaConsole/src/main/resources/employees.csv";

    public void addEmployee(Employee emp) throws DuplicateException {
        if(employees.stream().anyMatch(e -> e.getId().equals(emp.getId()))) {
            throw new DuplicateException("Id da ton tai!");
        }
        employees.add(emp);
    }

    public void removeEmployee(String id){
        boolean isRemoved = employees.removeIf(e -> e.getId().equals(id));
        if(isRemoved) {
            System.out.println("Da xoa id: "+ id);
        }
        else{
            System.out.println("Id khong ton tai");
        }
    }

    public Employee findById(String id){
        return employees.stream().filter(e -> e.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

    public void updateEmployee (String id, String name, String mail, String phone, String derpartment,
                                String type, double salary, LocalDate hireDate, boolean isActive){
        try{
            Employee emp = findById(id);
            if(emp.getDepartment().equalsIgnoreCase(derpartment)) {
                emp.setName(name);
                emp.setEmail(mail);
                emp.setPhone(phone);
                emp.setDepartment(derpartment);
                emp.setType(type);
                emp.setSalary(salary);
                emp.setHireDate(hireDate);
                emp.setActive(isActive);
            } else {
                String newId = this.generateAutoId(derpartment);
                Employee newEmployee = new Employee(newId, name, mail, phone, derpartment, type, salary, hireDate, isActive);
                employees.remove(emp);
                employees.add(newEmployee);
                System.out.println("Chuyen phong ban thanh cong. Nhan vien da duoc cap nhat ID moi: " + newId);
            }
        }catch (InvalidFormatException | MinusSalaryException ex){
            System.out.println("Loi: " + ex.getMessage());
        }
    }

    public void displayAll(){
        if(employees.isEmpty()){
            System.out.println("Khong co nhan vien nao");
            return;
        }
        employees.forEach(System.out::println);
    }

    public void search(String keyword, int searchType, Double minSalary, Double maxSalary){
        List<Employee> result = employees.stream()
                .filter(e -> {
                    if (keyword == null) return true;
                    return switch (searchType) {
                        case 1 -> StringUtils.containsIgnoreCase(e.getName(), keyword);
                        case 2 -> StringUtils.containsIgnoreCase(e.getDepartment(), keyword);
                        case 3 -> StringUtils.containsIgnoreCase(e.getType(), keyword);
                        default -> false;
                    };
                })
                .filter(e -> (minSalary == null || e.getSalary() >= minSalary))
                .filter(e -> (maxSalary == null || e.getSalary() <= maxSalary))
                .toList();
        result.forEach(System.out::println);
    }

    public void sort(int choice){
        switch(choice){
            case 1 -> employees.sort(Comparator.comparing(Employee::getSalary)); //tang dan
            case 2 -> employees.sort(Comparator.comparing(Employee::getSalary).reversed()); //giam dan
            case 3 -> employees.sort(Comparator.comparing(Employee::getName));
            case 4 -> employees.sort(Comparator.comparing(Employee::getName).reversed());
            case 5 -> employees.sort(Comparator.comparing(Employee::getHireDate));
            case 6 -> employees.sort(Comparator.comparing(Employee::getHireDate).reversed());
            default -> System.out.println("Tieu chi khong hop le.");
        }
        System.out.println("Da sap xep");
    }

    public void statistics(){
        if(employees.isEmpty()){
            System.out.println("Khong co du lieu nhan vien");
            return;
        }
        long totalEmployee = employees.size();
        double totalSalary = employees.stream().mapToDouble(Employee::getSalary).sum();
        double averageSalary = employees.stream().mapToDouble(Employee::getSalary).average().orElse(0.0);
        double maxSalary = employees.stream().mapToDouble(Employee::getSalary).max().orElse(0.0);
        System.out.println("Thong ke chung");
        System.out.printf("Tong nhan vien: %d | Tong quy luong: %.2f " +
                "| Luong trung binh: %.2f | Luong cao nhat: %.2f",
                totalEmployee, totalSalary, averageSalary, maxSalary);
    }

    public void top3Salary(){
        employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(3)
                .forEach(System.out::println);
    }

    public void groupByDepartment(){
        Map<String, List<Employee>> grouped = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        grouped.forEach((department, list) ->{
            System.out.println("Phong ban: " + department);
            list.forEach(e -> System.out.println("\t" +e));
        });
    }

    public void countActiveEmployees(){
        long count = employees.stream().filter(Employee::isActive).count();
        System.out.println("So nhan vien Active: " + count);
    }

    public void saveFile(){
        File file = new File(CSV_PATH);
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            for(Employee e: employees){
                bw.write(e.toCSV());
                bw.newLine();
            }
            System.out.println("Luu file thanh cong");
        } catch (IOException ex){
            System.out.println("Loi ghi file: " + ex.getMessage());
        }
    }

    public void loadFile(){
        File file = new File(CSV_PATH);
        if(!file.exists() || file.length() == 0){
            System.out.println("File chua co du lieu.");
            return;
        }
        employees.clear();
        System.out.println("Du lieu duoc tai tu file");
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null){
                String[] data = line.split(",");
                if(data.length >= 9){
                    try {
                        Employee emp = new Employee(
                                data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(),
                                data[4].trim(), data[5].trim(), Double.parseDouble(data[6].trim()),
                                LocalDate.parse(data[7].trim()), Boolean.parseBoolean(data[8].trim())
                        );
                        employees.add(emp);
                    } catch (InvalidFormatException | MinusSalaryException ex) {
                        System.out.println("Loi du lieu dong: " + line);
                    } catch (Exception ex){
                        System.out.println("Loi du lieu: " + line);
                    }
                }
            }
        } catch(IOException ex){
            System.out.println("Loi doc file: " + ex.getMessage());
        }
    }
    public String generateAutoId(String departmentCode) {
        if (departmentCode == null || departmentCode.trim().isEmpty()) {
            departmentCode = "NONE";
        }

        String prefix = departmentCode.trim().toUpperCase();
        int maxId = 0;

        for (Employee e : employees) {
            String currentIdStr = e.getId();
            if (currentIdStr.startsWith(prefix)) {
                try {
                    String numPart = currentIdStr.substring(prefix.length());
                    int currentId = Integer.parseInt(numPart);
                    if (currentId > maxId) {
                        maxId = currentId;
                    }
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return prefix + String.format("%02d", maxId + 1);
    }
}
