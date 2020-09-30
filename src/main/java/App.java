import Dao.DepartmentDao;
import Dao.Sql2oDepartmentDao;
import Dao.Sql2oEmployeeDao;
import Dao.Sql2oNewsDao;
import com.google.gson.Gson;
import exceptions.ApiExceptions;
import models.Department;
import models.Employee;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
public class App{
    public static void main(String[] args) {

        Sql2oDepartmentDao departmentDao;
        Sql2oEmployeeDao employeeDao;
        Sql2oNewsDao newsDao;
        Connection conn;
        Gson gson = new Gson();


        String connectionString = "jdbc:postgresql://localhost:5432/news_portal";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Georgedatabase1");

        departmentDao = new Sql2oDepartmentDao(sql2o);
        employeeDao = new Sql2oEmployeeDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        conn = sql2o.open();

        //Getting news
        get("/news", "application/json", (request, response) -> {
            return gson.toJson(newsDao.getAll());
        });

        //getting employees
        get("/employees", "application/json", (request, response) -> {
            return gson.toJson(employeeDao.getAll());
        });

        get("/departments/:id/employees", "application/json", (req, res) -> {
            int departmentid = Integer.parseInt(req.params("id"));
            Department departmentToFind = departmentDao.findById(departmentid);
            if (departmentToFind == null){
                throw new Error(String.format("No Department with the id: \"%s\" exists", req.params("id")));
            }
            else if (departmentDao.getAllEmployeesByDepartment(departmentid).size()==0){
                return "{\"message\":\"I'm sorry, but no users are listed for this Department.\"}";
            }
            else {
                return gson.toJson(departmentDao.getAllEmployeesByDepartment(departmentid));
            }
        });

        //getting each employee
        get("employees/:id", "application/json", (request, response) -> {
            int target = Integer.parseInt(request.params("id"));
            Employee employee = employeeDao.findById(target);
            if(employee != null){
                return gson.toJson(employee);
            }else{
                throw new Error("Employee with that Id does not exist");
            }
        });

        get("/departments/:id", "application/json", (request, response) -> { //accept a request in format JSON from an app
            response.type("application/json");
            int departmentId = Integer.parseInt(request.params("id"));

            if (departmentDao.findById(departmentId) == null){
                throw new ApiExceptions(404, String.format("No Department with the id: \"%s\" exists", request.params("id")));
            }
            return gson.toJson(departmentDao.findById(departmentId));
        });


        //getting departments
        get("/departments", "application/json", (request, response) -> {
            return gson.toJson(departmentDao.getAll());
        });

        //posting Departments
        post("/departments/new", "application/json", (request, response)->{
            Department department = gson.fromJson(request.body(), Department.class);
            departmentDao.add(department);
            response.status(201);
            return gson.toJson(department);
        });

        //posting employees
        post("/employees/new", "application/json", (request, response) -> {
            Employee employee = gson.fromJson(request.body(), Employee.class);
            employeeDao.add(employee);
            response.status(201);
            return gson.toJson(employee);
        });



        //Posting news
        post("/news/new", "application/json", (request, response) -> {
            News news = gson.fromJson(request.body(), News.class);
            newsDao.add(news);
            response.status(201);
            return gson.toJson(news);
        });


        //FILTERS
        exception(ApiExceptions.class, (exc, req, res) -> {
            ApiExceptions err = exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json"); //after does not run in case of an exception.
            res.status(err.getStatusCode()); //set the status
            res.body(gson.toJson(jsonMap));  //set the output.
        });

        after((request, response) ->{
            response.type("application/json");
        });
    }
}