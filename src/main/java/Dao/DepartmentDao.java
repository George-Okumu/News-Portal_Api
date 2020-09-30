package Dao;

import models.Department;
import models.Employee;

import java.util.List;

public interface DepartmentDao {
    //create
    void add (Department department);
    void addDepartmentToEmployee(Department department, Employee employee);


    //read
    List<Department> getAll();
    Department findById(int id);

    List<Employee> getAllEmployeesByDepartment(int departmentid);


    //update
    void update(int id, String departmentName, String description, String numberOfEmployees);

    //delete
    void deleteById(int id);
    void clearAll();
}
