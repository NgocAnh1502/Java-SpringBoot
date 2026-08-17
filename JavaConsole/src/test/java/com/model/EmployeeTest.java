package com.model;

import com.exceptions.InvalidFormatException;
import com.exceptions.MinusSalaryException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    @DisplayName("Test khởi tạo nhân viên với Email sai định dạng")
    void testEmployeeCreation_InvalidEmail() {
        InvalidFormatException exception = assertThrows(InvalidFormatException.class, () -> {
            new Employee("IT01", "Test", "email-khong-co-a-cong", "0123456789",
                    "IT", "FT", 1000, LocalDate.now(), true);
        });
        assertEquals("Email sai dinh dang", exception.getMessage());
    }

    @Test
    @DisplayName("Test khởi tạo nhân viên với Số điện thoại sai định dạng")
    void testEmployeeCreation_InvalidPhone() {
        InvalidFormatException exception = assertThrows(InvalidFormatException.class, () -> {
            new Employee("IT01", "Test", "test@gmail.com", "01234", // Chỉ có 5 số
                    "IT", "FT", 1000, LocalDate.now(), true);
        });
        assertEquals("So dien thoai sai dinh dang", exception.getMessage());
    }

    @Test
    @DisplayName("Test khởi tạo nhân viên với Lương âm")
    void testEmployeeCreation_MinusSalary() {
        MinusSalaryException exception = assertThrows(MinusSalaryException.class, () -> {
            new Employee("IT01", "Test", "test@gmail.com", "0123456789",
                    "IT", "FT", -500, LocalDate.now(), true);
        });
        assertEquals("Luong khong the am", exception.getMessage());
    }
}