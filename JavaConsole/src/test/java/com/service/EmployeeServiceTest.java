package com.service;

import com.exceptions.DuplicateException;
import com.exceptions.InvalidFormatException;
import com.exceptions.MinusSalaryException;
import com.model.Employee;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    private EmployeeService service;
    private Employee sampleEmployee;

    @BeforeEach
    void setUp() throws InvalidFormatException, MinusSalaryException {
        service = new EmployeeService();
        sampleEmployee = new Employee("IT01", "Nguyen Van A", "a.nguyen@test.com", "0987654321",
                "IT", "Fulltime", 15000, LocalDate.now(), true);
    }

    @Test
    @DisplayName("Test tạo ID tự động khi danh sách trống")
    void testGenerateAutoId_EmptyList() {
        String idIT = service.generateAutoId("IT");
        String idHR = service.generateAutoId("HR");

        assertEquals("IT01", idIT);
        assertEquals("HR01", idHR);
    }

    @Test
    @DisplayName("Test tạo ID tự động khi danh sách đã có nhân viên cùng phòng ban")
    void testGenerateAutoId_ExistingDepartment() throws DuplicateException {
        service.addEmployee(sampleEmployee); // Đã có IT01

        String nextId = service.generateAutoId("IT");
        assertEquals("IT02", nextId, "ID tiep theo cua phong IT phai la IT02");
    }

    @Test
    @DisplayName("Test thêm nhân viên thành công")
    void testAddEmployee_Success() {
        assertDoesNotThrow(() -> {
            service.addEmployee(sampleEmployee);
        });

        Employee found = service.findById("IT01");
        assertNotNull(found);
        assertEquals("Nguyen Van A", found.getName());
    }

    @Test
    @DisplayName("Test ném ngoại lệ khi thêm nhân viên trùng ID")
    void testAddEmployee_DuplicateId() throws DuplicateException {
        service.addEmployee(sampleEmployee); // Thêm lần 1

        DuplicateException exception = assertThrows(DuplicateException.class, () -> {
            service.addEmployee(sampleEmployee); // Thêm lần 2 cố tình gây lỗi
        });

        assertEquals("Id da ton tai!", exception.getMessage());
    }

    @Test
    @DisplayName("Test cập nhật thông tin nhân viên")
    void testUpdateEmployee_Success() throws DuplicateException {
        service.addEmployee(sampleEmployee);

        // Gọi hàm update sửa tên và lương
        service.updateEmployee("IT01", "Nguyen Van B", "b.nguyen@test.com", "0987654321",
                "IT", "Fulltime", 20000, LocalDate.now(), true);

        Employee updatedEmp = service.findById("IT01");
        assertEquals("Nguyen Van B", updatedEmp.getName());
        assertEquals(20000, updatedEmp.getSalary());
    }

    @Test
    @DisplayName("Test xóa nhân viên")
    void testRemoveEmployee() throws DuplicateException {
        service.addEmployee(sampleEmployee);

        // Xóa ID tồn tại
        service.removeEmployee("IT01");
        assertNull(service.findById("IT01"), "Nhan vien nen bi xoa khoi danh sach");
    }
}
