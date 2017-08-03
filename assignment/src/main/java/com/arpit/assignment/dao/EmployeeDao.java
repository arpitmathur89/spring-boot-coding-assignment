package com.arpit.assignment.dao;

import com.arpit.assignment.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDao {


    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    public String createRecord(Employee emp) {
        String insertQuery = "INSERT INTO employee (id, username, name) VALUES (:id, :username, :name)";
        Map<String, Object> map = new HashMap<>();
        map.put("id", (emp.getId()));
        map.put("username", emp.getUsername());
        map.put("name", emp.getName());
        SqlParameterSource nameParameters = new MapSqlParameterSource(map);
        try {
            int ans = jdbcTemplate.update(insertQuery, nameParameters);
            if(ans == 0){
                return "not inserted";
            }else {
                return "inserted";
            }
        }
        catch (DataAccessException e){
            return "Couldn't add record!! Try Again..";
        }
    }

    public List<Employee> readEntries() {
        List<Employee> empList = new ArrayList<>();
        String readQuery = "SELECT * FROM employee";
        SqlParameterSource nameParameters = new MapSqlParameterSource();
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(readQuery, nameParameters);
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("id"));
                e.setUsername(rs.getString("username"));
                e.setName(rs.getString("name"));
                empList.add(e);
            }
            return empList;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public String deleteRecord(String id) {
        String deleteQuery = "DELETE from employee where id =:id";
        SqlParameterSource nameParam = new MapSqlParameterSource("id", id);
        try {
            int ans = jdbcTemplate.update(deleteQuery, nameParam);
            if(ans == 0){
                return "not deleted";
            }else {
                return "deleted";
            }
        }
        catch (DataAccessException e){
            return "Couldn't delete record!! Check the id again";
        }
    }
}
