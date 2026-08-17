package com.model;

import com.exceptions.InvalidFormatException;
import com.exceptions.MinusSalaryException;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class Employee{
    private final String id;
    private String name;
    private String email;
    private String phone;
    private String department;
    private String type;
    private double salary;
    private LocalDate hireDate;
    private boolean isActive;

    private static final String EMAIL_FORMAT = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_FORMAT = "^[0-9]{10}$";

    public Employee(String id, String name, String email, String phone, String department,
                    String type, double salary, LocalDate hireDate, boolean isActive) throws InvalidFormatException, MinusSalaryException {
        this.id = id;
        this.name = name;
        setEmail(email);
        setPhone(phone);
        this.department = department;
        this.type = type;
        setSalary(salary);
        this.hireDate = hireDate;
        this.isActive = isActive;
    }

    public void setEmail(String email) throws InvalidFormatException {
        if(!Pattern.matches((EMAIL_FORMAT), email)){
            throw new InvalidFormatException("Email sai dinh dang");
        }
        this.email = email;
    }

    public void setPhone(String phone) throws InvalidFormatException {
        if(!Pattern.matches((PHONE_FORMAT), phone)){
            throw new InvalidFormatException("So dien thoai sai dinh dang");
        }
        this.phone = phone;
    }

    public void setSalary(double salary) throws MinusSalaryException {
        if(salary < 0){
            throw new MinusSalaryException("Luong khong the am");
        }
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }
    public void setActive(boolean active) {
        isActive = active;
    }


    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getDepartment() {
        return department;
    }
    public String getType() {
        return type;
    }
    public double getSalary() {
        return salary;
    }
    public LocalDate getHireDate() {
        return hireDate;
    }
    public boolean isActive() {
        return isActive;
    }

    @Override
    public String toString(){
        return "Id: " + id + " Name: " + name + " Email: " + email + " Phone: " + phone
                + " Department: " + department + " Type: " + type + " Salary: " + salary
                + " Hire Date: " + hireDate + " Active: " + isActive;
    }

    public String toCSV(){
        return id + " , " + name + " , " + email + " , " + phone + " , " + department + " , "
                + type + " , " + salary + " , " + hireDate + " , " + isActive;
    }
}