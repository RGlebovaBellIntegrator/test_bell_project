package ru.bellintegrator.users.view;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class UsersView {
    @ApiModelProperty(hidden = true)
    public String id;

    public String login;

    public String password;

    public String firstName;

    public String secondName;

    public String middleName;
    public String position_statement;


    public String phone;
    public String docNumber;
    public Date docDate;

    public byte isIdentified;

    public UsersView() {

    }

    public UsersView(String login, String password, String firstName, String secondName, String middleName, String position_statement,
                     String phone, String docNumber, Date docDate, byte isIdentified) {
        this.login=login;
        this.password=password;
        this.firstName=firstName;
        this.secondName=secondName;
        this.middleName=middleName;
        this.position_statement=position_statement;
        this.phone = phone;
        this.docNumber=docNumber;
        this.docDate = docDate;
        this.isIdentified = isIdentified;
    }
}
