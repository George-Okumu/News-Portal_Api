package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void employee_instantiatesCorrectly() throws  Exception {
        Employee testEmployee = setUpNewEmployee();
        assertTrue(true);
    }

    @Test
    public void getNameReturnsCorrectEmployeeName() throws Exception{
        Employee testEmployee = setUpNewEmployee();
        assertEquals("George", testEmployee.getEmployeeName());
    }

    @Test
    public void getPositionReturnsCorrectEmployeePosition() throws Exception{
        Employee testEmployee = setUpNewEmployee();
        assertEquals("Staff", testEmployee.getPosition());
    }

    @Test
    public void employee_getsEmployeeRoleReturnsCorrectEmployeeRole() throws Exception{
        Employee testEmployee = setUpNewEmployee();
        assertEquals("Managerial Staff", testEmployee.getRole());
    }

    @Test
    public void employee_getsEmployeeEmailCorrectly() throws Exception{
        Employee testEmployee = setUpNewEmployee();
        assertEquals("gokumu12@gmail.com", testEmployee.getEmail());
    }

    @Test
    public void employee_getsEmailCorrectly_string() throws Exception{
        Employee testEmployee = setUpNewEmployee();
        assertEquals("0798765432", testEmployee.getPhoneNumber());
    }

    @Test
    public void employee_correctlyGetsDepartmentId() throws Exception{
        Employee testEmployee = setUpNewEmployee();
        assertEquals(10, testEmployee.getDepartmentId());
    }

    @Test
    public void set_setsEmployeeNameCorrectly() throws Exception{
        Employee testEmployee = setUpNewEmployee();
        testEmployee.setEmployeeName("George");
        assertEquals("George", testEmployee.getEmployeeName());
    }

    @Test
    public void set_setsEmployeePositionCorrectly() throws Exception{
        Employee testEmployee = setUpNewEmployee();
        testEmployee.setPosition("Staff");
        assertEquals("Staff", testEmployee.getPosition());
    }

    @Test
    public void set_setsEmployeeRoleCorrectly() throws Exception{
        Employee testEmployee = setUpNewEmployee();
        testEmployee.setRole("Managerial Staff");
        assertNotEquals("Secretary", testEmployee.getRole());
    }
    @Test
    public void set_setsEmployeeEmailCorrectly() throws Exception{
        Employee testEmployee = setUpNewEmployee();
        testEmployee.setEmail("gokumu12@gmail.com");
        assertNotEquals("George", testEmployee.getEmail());
    }

    @Test
    public void set_setsEmployeePhoneNumberCorrectly() throws Exception{
        Employee testEmployee = setUpNewEmployee();
        testEmployee.setPhoneNumber("0798765432");
        assertNotEquals("George", testEmployee.getPhoneNumber());
    }

    @Test
    public void set_setsDepartmentIdCorrectly() throws Exception{
        Employee testEmployee = setUpNewEmployee();
        testEmployee.setDepartmentId(10);
        assertNotEquals(18990, testEmployee.getDepartmentId());
    }

    @Test
    public void equals_returnsAllInstancesOfEmployeeTheSame() throws Exception{
        Employee oneEmployee = setUpNewEmployee();
        Employee thirdEmployee = setUpNewEmployee();
        assertEquals(true, oneEmployee.equals(thirdEmployee));
    }

    //helper for tests
    public Employee setUpNewEmployee(){
        return new Employee("George","Staff", "Managerial Staff", "gokumu12@gmail.com","0798765432",10);
    }

}