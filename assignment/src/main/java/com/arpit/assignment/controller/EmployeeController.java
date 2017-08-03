package com.arpit.assignment.controller;

import com.arpit.assignment.model.Employee;
import com.arpit.assignment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createEntry(@RequestBody Employee emp){
        return employeeService.createRecord(emp);
    }

    @RequestMapping(value = "read", method = RequestMethod.GET)
    public List<Employee> readEntries(){

        return employeeService.readEntries();

    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteEntry(@RequestParam("id") String id){
        return employeeService.deleteRecord(id);
    }

}
