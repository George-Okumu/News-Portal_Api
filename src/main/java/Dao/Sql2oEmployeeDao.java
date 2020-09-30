package Dao;

import models.Department;
import models.Employee;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oEmployeeDao implements EmployeeDao{
    private final Sql2o sql2o;
    public Sql2oEmployeeDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(Employee employee) {
        String sql = "INSERT INTO employees (employeeName,position, role, email, phoneNumber, departmentId) VALUES (:employeeName, :position, :role, :email, :phoneNumber, :departmentId);"; //if you change your model, be sure to update here as well!
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(employee)
                    .executeUpdate()
                    .getKey();
            employee.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public List<Employee> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM employees")
                    .executeAndFetch(Employee.class);

        }
    }

    public List<Department> getAllDepartmentsForAEmployee(int employeeId) {
        ArrayList<Department> departments = new ArrayList<>();

        String joinQuery = "SELECT departmentid FROM departments_employees WHERE employeeid = :employeeid";

        try (Connection con = sql2o.open()) {
            List<Integer> alldepartmentids = con.createQuery(joinQuery)
                    .addParameter("employeeid", employeeId)
                    .executeAndFetch(Integer.class); //what is happening in the lines above?
            for (Integer departmentid : alldepartmentids){
                String DepartmentQuery = "SELECT * FROM departments WHERE id = :departmentid";
                departments.add(
                        con.createQuery(DepartmentQuery)
                                .addParameter("departmentid", departmentid)
                                .executeAndFetchFirst(Department.class));
            } //why are we doing a second sql query - set?
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return departments;
    }

    @Override
    public Employee findById(int id) {
        String sql = "SELECT * FROM employees WHERE id = :id";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Employee.class);
        }
    }

    @Override
    public void addEmployeeToDepartment(Employee employee, Department department){
        String sql = "INSERT INTO departments_employees (departmentsid, employeeid) VALUES (:departmentId, :employeeId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("departmentId", department.getId())
                    .addParameter("employeeId", employee.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from employees WHERE id = :id";
        String deleteJoin = "DELETE from departments_employees WHERE employeeid = :employeeId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("restaurantId", id)
                    .executeUpdate();

        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }


    @Override
    public void clearAll() {
        String sql = "DELETE FROM employees";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }
}
