package org.test.vaadin.ui;


import org.springframework.util.StringUtils;
import org.test.vaadin.model.Employee;
import org.test.vaadin.repository.EmployeeRepository;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    private EmployeeRepository employeeRepository;

    private EmployeeEditor editor;

    Grid<Employee> grid;

    TextField filter;

    private Button addNewBtn;

    
    public MainView(EmployeeRepository repo, EmployeeEditor editor) {
        this.employeeRepository = repo;
        this.editor = editor;
        this.grid = new Grid<>(Employee.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New employee", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, editor);

        grid.setHeight("200px");
        grid.setColumns("id", "firstName", "lastName");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter by last name");

        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listEmployees(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editEmployee(e.getValue());
        });

        addNewBtn.addClickListener(e -> editor.editEmployee(new Employee("", "")));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listEmployees(filter.getValue());
        });

        listEmployees(null);
    }

    void listEmployees(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(employeeRepository.findAll());
        } else {
            grid.setItems(employeeRepository.findByLastNameStartsWithIgnoreCase(filterText));
        }
    }
}