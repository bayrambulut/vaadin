package org.test.vaadin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.vaadin.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByLastNameStartsWithIgnoreCase(String lastName);
}

