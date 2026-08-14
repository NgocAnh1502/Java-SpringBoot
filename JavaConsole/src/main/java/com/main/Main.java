package com.main;

import com.exceptions.DuplicateException;
import com.exceptions.InvalidFormatException;
import com.exceptions.MinusSalaryException;
import com.model.Employee;
import com.service.EmployeeService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static void main(String[] args) {
        EmployeeService service = new EmployeeService();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            service.loadFile();
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

            scheduler.scheduleAtFixedRate(() -> {
                service.saveFile();
                System.out.println("\nAuto save file employees.cvs");
            }, 60, 60, TimeUnit.SECONDS);

            scheduler.scheduleAtFixedRate(() ->{
                System.out.println("\nTong quy luong hien tai: " + service.getTotalSalary());
            }, 30, 30, TimeUnit.SECONDS);

            boolean running = true;
            while (running){
                System.out.println("\n+--------------------------------------------------------------+");
                System.out.println("|                        QUAN LY NHAN VIEN                     |");
                System.out.println("+--------------------------------------------------------------+");
                System.out.println("| 1. Them nhan vien                |  2. Xoa nhan vien         |");
                System.out.println("| 3. Sua thong tin nhan vien       |  4. Tim kiem              |");
                System.out.println("| 5. Sap xep                       |  6. Thong ke              |");
                System.out.println("| 7. Luu file                      |  8. Hien thi danh sach    |");
                System.out.println("| 9. Thoat chuong trinh            |                           |");
                System.out.println("+--------------------------------------------------------------+");
                System.out.print("Chon menu: ");

                int choice = Integer.parseInt(br.readLine());

                switch (choice) {
                    case 1:
                        try{
                            System.out.println("Nhap du lieu: ");
                            System.out.print("ID: "); String id = br.readLine();
                            System.out.print("Name: "); String name = br.readLine();
                            System.out.print("Email: "); String email = br.readLine();
                            System.out.print("Phone: "); String phone = br.readLine();
                            System.out.print("Department: "); String department = br.readLine();
                            System.out.print("Type: "); String type = br.readLine();
                            System.out.print("Salary: "); double salary = Double.parseDouble(br.readLine());
                            System.out.print("Hire date (dd/MM/yyyy): "); LocalDate hireDate = LocalDate.parse(br.readLine(), DATE_FORMAT);
                            System.out.print("Active: "); boolean active = Boolean.parseBoolean(br.readLine());

                            Employee emp = new Employee(id, name, email, phone, department, type, salary, hireDate, active);
                            service.addEmployee(emp);
                            System.out.println("Them thanh cong!");
                        } catch (DuplicateException | InvalidFormatException | MinusSalaryException ex){
                            System.out.println("Loi: " + ex.getMessage());
                        } catch (Exception ex){
                            System.out.println("Loi nhap du lieu: " + ex.getMessage());
                        }
                        break;
                    case 2:
                        System.out.print("Nhap id can xoa: ");
                        service.removeEmployee(br.readLine());
                        break;
                    case 3:
                        System.out.print("Nhap id can sua: ");
                        String id = br.readLine();
                        Employee employee = service.findById(id);
                        try{
                            if (employee != null){
                                System.out.println("Nhap du lieu can sua: ");
                                System.out.print("Name: "); String name = br.readLine();
                                System.out.print("Email: "); String email = br.readLine();
                                System.out.print("Phone: "); String phone = br.readLine();
                                System.out.print("Department: "); String department = br.readLine();
                                System.out.print("Type: "); String type = br.readLine();
                                System.out.print("Salary: "); double salary = Double.parseDouble(br.readLine());
                                System.out.print("Hire date (dd/MM/yyyy): "); LocalDate hireDate = LocalDate.parse(br.readLine(), DATE_FORMAT);
                                System.out.print("Active: "); boolean active = Boolean.parseBoolean(br.readLine());

                                service.updateEmployee(id, name, email, phone, department, type, salary, hireDate, active);
                                System.out.println("Sua thanh cong");
                            }
                        } catch (InvalidFormatException | MinusSalaryException ex){
                            System.out.println("Loi: " + ex.getMessage());
                        }  catch (Exception ex){
                            System.out.println("Loi nhap du lieu: " + ex.getMessage());
                        }
                        break;
                    case 4:
                        System.out.print("1. Tim theo ten     | 2. Tim theo phong ban   \n" +
                                         "3. Tim theo loai    | 4. Tim theo khoang luong\n" +
                                         "Moi ban nhap lua chon: ");
                        int b = Integer.parseInt(br.readLine());
                        if(b == 1){
                            System.out.print("Moi nhap ten can tim: ");
                            String ten = br.readLine();
                            service.search(ten, 1, null, null);
                        } else if (b == 2) {
                            System.out.print("Moi nhap phong ban can tim: ");
                            String phongBan = br.readLine();
                            service.search(phongBan, 2, null, null);
                        } else if (b == 3) {
                            System.out.print("Moi nhap loai can tim: ");
                            String loai = br.readLine();
                            service.search(loai, 3, null, null);
                        } else if (b == 4) {
                            Double min = null;
                            Double max = null;
                            try {
                                System.out.print("Moi nhap luong toi thieu (An Enter de bo qua): ");
                                String minInput = br.readLine().trim();
                                if (!minInput.isEmpty()) min = Double.parseDouble(minInput);

                                System.out.print("Moi nhap luong toi da (An Enter de bo qua): ");
                                String maxInput = br.readLine().trim();
                                if (!maxInput.isEmpty()) max = Double.parseDouble(maxInput);

                                if (min != null && max != null && min > max) {
                                    System.out.println("Loi: Luong toi thieu khong the lon hon luong toi da.");
                                } else {
                                    service.search(null, 4, min, max);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Loi: Vui long chi nhap so.");
                            }
                        } else {
                            System.out.println("Khong co tim kiem nay!");
                        }
                        break;
                    case 5:
                        try {
                            System.out.println("1: Luong tang dan   | 2: Luong giam dan\n" +
                                               "3. Ten A-Z          | 4. Ten Z-A\n" +
                                               "5. Tuyen dung truoc | 6. Tuyen dung sau");
                            System.out.print("Moi nhap lua chon: ");
                            int a = Integer.parseInt(br.readLine());
                            service.sort(a);
                        } catch(NumberFormatException ex){
                            System.out.println("Nhap sai: " + ex);
                        }
                        break;
                    case 6:
                        try {
                            System.out.print("1. Thong ke chung      | 2. Top 3 luong cao nhat\n" +
                                             "3. Nhom theo phong ban | 4. So nhan vien active \n" +
                                             "Moi ban nhap lua chon: ");
                            int i = Integer.parseInt(br.readLine());
                            if (i == 1) {
                                service.statistics();
                            } else if (i == 2) {
                                service.top3Salary();
                            } else if (i == 3) {
                                service.groupByDepartment();
                            } else if (i == 4) {
                                service.countActiveEmployees();
                            } else {
                                System.out.println("Khong co thong ke nay!");
                            }
                        } catch(NumberFormatException ex){
                            System.out.println("Nhap sai: " + ex);
                        }
                        break;
                    case 7:
                        service.saveFile();
                        break;
                    case 8:
                        service.loadFile();
                        service.displayAll();
                        break;
                    case 9:
                        running = false;
                        scheduler.shutdown();
                        System.out.println("Da thoat chuong trinh");
                        break;
                    default:
                        System.out.print("Lua chon khong phu hop, moi chon lai");
                        break;
                }
            }
        } catch(Exception ex){
            System.out.println("Loi " + ex.getMessage());
        }
    }
}
