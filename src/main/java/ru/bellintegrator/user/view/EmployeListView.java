package ru.bellintegrator.user.view;

import io.swagger.annotations.ApiModelProperty;

public class EmployeListView {
    @ApiModelProperty(hidden = true)
    public String id;
    public String firstName;

    public String secondName;

    public String middleName;
    public String position;


    public EmployeListView() {

    }

    public EmployeListView(String firstName, String secondName, String middleName, String position) {
        this.firstName=firstName;
        this.secondName=secondName;
        this.middleName=middleName;
        this.position = position;
    }
}
