package com.rest.employeeservice.controller;

import com.rest.employeeservice.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class EmployeeController {
    public static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    Map<Integer, Employee> empData = new HashMap<>();

    @RequestMapping(value = EmpRestURIConstants.DUMMY_EMP, method = RequestMethod.GET)
    public @ResponseBody
    Employee getDummyEmployee() {
        logger.info("START getDummyEmployee");
        Employee employee = new Employee();
        employee.setId(9999);
        employee.setName("Dummy");
        employee.setCreatedDate(new Date());
        empData.put(9999, employee);

        return employee;
    }

    @RequestMapping(value = EmpRestURIConstants.GET_EMP, method = RequestMethod.GET)
    public @ResponseBody Employee getEmployee(@PathVariable("id") int empId) {
        logger.info("Start getEmployee. ID = " + empId);
        return empData.get(empId);
    }

    @RequestMapping(value = EmpRestURIConstants.GET_ALL_EMP, method = RequestMethod.GET)
    public @ResponseBody
    List<Employee> getAllEmployees() {
        logger.info("Start getAllEmployees");
        List<Employee> emps = new ArrayList<>();
        Set<Integer> empIdKeys = empData.keySet();
        for(Integer i : empIdKeys) {
            emps.add(empData.get(i));
        }
        return emps;
    }

    @RequestMapping(value = EmpRestURIConstants.CREATE_EMP, method = RequestMethod.POST)
    public @ResponseBody Employee createEmployee(@RequestBody Employee employee) {
        logger.info("Start CreateEmployee");;
        employee.setCreatedDate(new Date());
        empData.put(employee.getId(), employee);
        return employee;
    }

    @RequestMapping(value = EmpRestURIConstants.DELETE_EMP, method = RequestMethod.PUT)
    public @ResponseBody Employee deleteEmployee(@PathVariable("id") int empId) {
        logger.info("Start deleteEmployee");
        Employee employee = empData.get(empId);
        empData.remove(empId);
        return employee;
    }

}
