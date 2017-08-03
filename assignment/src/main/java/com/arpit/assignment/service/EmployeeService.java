package com.arpit.assignment.service;

import com.arpit.assignment.dao.EmployeeDao;
import com.arpit.assignment.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    public String createRecord(Employee emp) {
        return employeeDao.createRecord(emp);
    }

    public List<Employee> readEntries() {
        return employeeDao.readEntries();
    }

    public String deleteRecord(String id) {
        return employeeDao.deleteRecord(id);
    }
}
