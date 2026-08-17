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
import java.time.format.DateTimeParseException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        EmployeeService service = new EmployeeService();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            service.loadFile();
            /* ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

            scheduler.scheduleAtFixedRate(() -> {
                service.saveFile();
                System.out.println("\n[System] Auto save file employees.csv");
            }, 60, 60, TimeUnit.SECONDS);

            scheduler.scheduleAtFixedRate(() -> {
                System.out.println("\n[System] Tong quy luong hien tai: " + service.getTotalSalary());
            }, 30, 30, TimeUnit.SECONDS); */

            boolean running = true;
            while (running) {
                printMenu();
                int choice = -1;
                try {
                    choice = Integer.parseInt(br.readLine());
                } catch (NumberFormatException e) {
                    System.out.println("Vui long nhap mot so nguyen.");
                    continue;
                }

                switch (choice) {
                    case 1 -> addEmployeeMain(br, service);
                    case 2 -> removeEmployeeMain(br, service);
                    case 3 -> updateEmployeeMain(br, service);
                    case 4 -> searchEmployeeMain(br, service);
                    case 5 -> sortEmployeeMain(br, service);
                    case 6 -> statisticsMain(br, service);
                    case 7 -> service.saveFile();
                    case 8 -> {
                        service.displayAll();
                    }
                    case 9 -> {
                        service.saveFile();
                        running = false;
                        //scheduler.shutdown();
                        System.out.println("Da thoat chuong trinh");
                    }
                    default -> System.out.println("Lua chon khong phu hop, moi chon lai");
                }
            }
        } catch (Exception ex) {
            System.out.println("Loi he thong: " + ex.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println("\n+--------------------------------------------------------------+");
        System.out.println("|                      QUAN LY NHAN VIEN                       |");
        System.out.println("+--------------------------------------------------------------+");
        System.out.println("| 1. Them nhan vien                |  2. Xoa nhan vien         |");
        System.out.println("| 3. Sua thong tin nhan vien       |  4. Tim kiem              |");
        System.out.println("| 5. Sap xep                       |  6. Thong ke              |");
        System.out.println("| 7. Luu file                      |  8. Hien thi danh sach    |");
        System.out.println("| 9. Thoat chuong trinh            |                           |");
        System.out.println("+--------------------------------------------------------------+");
        System.out.print("Chon menu: ");
    }

    private static void addEmployeeMain(BufferedReader br, EmployeeService service) {
        try {
            System.out.println("Nhap du lieu: ");
            System.out.print("Name: "); String name = br.readLine();
            System.out.print("Email: "); String email = br.readLine();
            System.out.print("Phone: "); String phone = br.readLine();
            System.out.print("Department (VD: IT, HR, MKT, MEDIA): "); String department = br.readLine().toUpperCase();
            String id = service.generateAutoId(department);
            System.out.println("ID (Tu dong tao): " + id);
            System.out.print("Type: "); String type = br.readLine();
            System.out.print("Salary: "); double salary = Double.parseDouble(br.readLine());
            System.out.print("Hire date (dd/MM/yyyy): "); LocalDate hireDate = LocalDate.parse(br.readLine(), DATE_FORMAT);
            System.out.print("Active (true/false): "); boolean active = Boolean.parseBoolean(br.readLine());

            Employee emp = new Employee(id, name, email, phone, department, type, salary, hireDate, active);
            service.addEmployee(emp);
            System.out.println("Them thanh cong!");
        } catch (DuplicateException | InvalidFormatException | MinusSalaryException | DateTimeParseException ex) {
            System.out.println("Loi: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Loi nhap du lieu: " + ex.getMessage());
        }
        service.saveFile();
    }

    private static void removeEmployeeMain(BufferedReader br, EmployeeService service) {
        try {
            System.out.print("Nhap id can xoa: ");
            service.removeEmployee(br.readLine());
        } catch (Exception ex) {
            System.out.println("Loi: " + ex.getMessage());
        }
    }

    private static void updateEmployeeMain(BufferedReader br, EmployeeService service) {
        try {
            System.out.print("Nhap id can sua: ");
            String id = br.readLine();
            Employee employee = service.findById(id);

            if (employee != null) {
                System.out.println("Nhap du lieu can sua (An Enter de giu nguyen gia tri cu): ");

                System.out.print("Name [" + employee.getName() + "]: ");
                String inputName = br.readLine();
                String name = inputName.isEmpty() ? employee.getName() : inputName;

                System.out.print("Email [" + employee.getEmail() + "]: ");
                String inputEmail = br.readLine();
                String email = inputEmail.isEmpty() ? employee.getEmail() : inputEmail;

                System.out.print("Phone [" + employee.getPhone() + "]: ");
                String inputPhone = br.readLine();
                String phone = inputPhone.isEmpty() ? employee.getPhone() : inputPhone;

                System.out.print("Department [" + employee.getDepartment() + "]: ");
                String inputDepartment = br.readLine().toUpperCase();
                String department = inputDepartment.isEmpty() ? employee.getDepartment() : inputDepartment;

                System.out.print("Type [" + employee.getType() + "]: ");
                String inputType = br.readLine();
                String type = inputType.isEmpty() ? employee.getType() : inputType;

                System.out.print("Salary [" + employee.getSalary() + "]: ");
                String inputSalary = br.readLine();
                double salary = inputSalary.isEmpty() ? employee.getSalary() : Double.parseDouble(inputSalary);

                System.out.print("Hire date (dd/MM/yyyy) [" + employee.getHireDate().format(DATE_FORMAT) + "]: ");
                String inputHireDate = br.readLine();
                LocalDate hireDate = inputHireDate.isEmpty() ? employee.getHireDate() : LocalDate.parse(inputHireDate, DATE_FORMAT);

                System.out.print("Active (true/false) [" + employee.isActive() + "]: ");
                String inputActive = br.readLine();
                boolean active = inputActive.isEmpty() ? employee.isActive() : Boolean.parseBoolean(inputActive);

                service.updateEmployee(id, name, email, phone, department, type, salary, hireDate, active);
                System.out.println("Cap nhat thong tin thanh cong!");
            } else {
                System.out.println("Khong tim thay nhan vien mang ID nay.");
            }
        } catch (InvalidFormatException | MinusSalaryException | DateTimeParseException ex) {
            System.out.println("Loi: Dữ liệu nhập vào không hợp lệ - " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Loi nhap du lieu: " + ex.getMessage());
        }
    }

    private static void searchEmployeeMain(BufferedReader br, EmployeeService service) {
        try {
            System.out.print("""
                    1. Tim theo ten     | 2. Tim theo phong ban  \s
                    3. Tim theo loai    | 4. Tim theo khoang luong
                    Moi ban nhap lua chon:\s""");
            int b = Integer.parseInt(br.readLine());

            switch (b) {
                case 1 -> {
                    System.out.print("Moi nhap ten can tim: ");
                    service.search(br.readLine(), 1, null, null);
                }
                case 2 -> {
                    System.out.print("Moi nhap phong ban can tim: ");
                    service.search(br.readLine(), 2, null, null);
                }
                case 3 -> {
                    System.out.print("Moi nhap loai can tim: ");
                    service.search(br.readLine(), 3, null, null);
                }
                case 4 -> {
                    Double min = null;
                    Double max = null;
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
                }
                default -> System.out.println("Khong co tim kiem nay!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Loi: Vui long chi nhap so.");
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }

    private static void sortEmployeeMain(BufferedReader br, EmployeeService service) {
        while (true) {
            try {
                System.out.println("""
                    1: Luong tang dan   | 2: Luong giam dan
                    3. Ten A-Z          | 4. Ten Z-A
                    5. Tuyen dung truoc | 6. Tuyen dung sau""");
                System.out.print("Moi nhap lua chon (1-6): ");
                int a = Integer.parseInt(br.readLine());
                if (a < 1 || a > 6) {
                    System.out.println("=> Lua chon khong hop le. Vui long nhap so tu 1 den 6.\n");
                    continue;
                }

                service.sort(a);
                service.displayAll();
                break;

            } catch (NumberFormatException ex) {
                System.out.println("=> Nhap sai dinh dang, phai la so. Vui long thu lai.\n");
            } catch (Exception ex) {
                System.out.println("=> Loi he thong: " + ex.getMessage());
                break;
            }
        }
    }

    private static void statisticsMain(BufferedReader br, EmployeeService service) {
        try {
            System.out.print("""
                    1. Thong ke chung      | 2. Top 3 luong cao nhat
                    3. Nhom theo phong ban | 4. So nhan vien active\s
                    Moi ban nhap lua chon:\s""");
            int i = Integer.parseInt(br.readLine());
            switch (i) {
                case 1 -> service.statistics();
                case 2 -> service.top3Salary();
                case 3 -> service.groupByDepartment();
                case 4 -> service.countActiveEmployees();
                default -> System.out.println("Khong co thong ke nay!");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Nhap sai định dạng số: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Loi: " + ex.getMessage());
        }
    }
}