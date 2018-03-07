package ru.bellintegrator.user.view;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class EmployeView {
    @ApiModelProperty(hidden = true)
    public String id;

    public String firstName;

    public String secondName;

    public String middleName;
    public String statement;


    public String phone;
    public int docCode;
    public String docNumber;
    public Date docDate;
    public int countryCode;
    public Boolean isIdentified;

    public EmployeView() {

    }

    public EmployeView(String firstName, String secondName, String middleName, String statement,
                       String phone, String docNumber, Date docDate, Boolean isIdentified) {
        this.firstName=firstName;
        this.secondName=secondName;
        this.middleName=middleName;
        this.statement=statement;
        this.phone = phone;
        this.docNumber=docNumber;
        this.docDate = docDate;
        this.isIdentified = isIdentified;
    }


}
