package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void department_instantiatesCorrectly_true() throws  Exception{
        Department testDepartment = setUpNewDepartment();
        assertTrue(true);
    }

    @Test
    public void department_getsDepartmentNameCorrectly_string() throws Exception{
        Department testDepartment = setUpNewDepartment();
        assertEquals("Bamburi Cement", testDepartment.getDepartmentName());
    }

    @Test
    public void getDescriptionReturnsCorrectDescription() throws Exception{
        Department testDepartment = setUpNewDepartment();
        assertEquals("Production of cement", testDepartment.getDescription());
    }

    @Test
    public void department_getsDepartmentsNumberOfEmployeesCorrectly() throws  Exception {
        Department testDepartment = setUpNewDepartment();
        assertEquals("2250", testDepartment.getNumberOfEmployees());
    }

    @Test
    public void setDepartmentNameSetsCorrectly() throws  Exception{
        Department testDepartment = setUpNewDepartment();
        testDepartment.setDepartmentName("Bamburi Cement");
        assertEquals("Bamburi Cement", testDepartment.getDepartmentName());
    }

    @Test
    public void department_setsDepartmentDescriptionCorrectly() throws  Exception{
        Department testDepartment = setUpNewDepartment();
        testDepartment.setDescription("Production of cement");
        assertNotEquals("Production of bread", testDepartment.getDescription());
    }

    @Test
    public void department_setsDepartmentsNumberOfEmployees() throws  Exception {
        Department testDepartment = setUpNewDepartment();
        testDepartment.setNumberOfEmployees("2250");
        assertNotEquals("1050", testDepartment.getNumberOfEmployees());
    }

    @Test
    public void equals_returnsAllInstancesOfDepartmentTrueIfTheyAreTheSame() throws Exception{
        Department firstDepartment = setUpNewDepartment();
        Department secondDepartment = setUpNewDepartment();
        assertEquals(true, firstDepartment.equals(secondDepartment));
    }

    //helper method for testing
    public Department setUpNewDepartment(){
        return new Department("Bamburi Cement", "Production of cement", "2250");
    }

}