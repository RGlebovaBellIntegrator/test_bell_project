package ru.bellintegrator.user.view;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class UserView {
    @ApiModelProperty(hidden = true)
    public String id;

    public String login;

    public String password;

    public String firstName;

    public String secondName;

    public String middleName;
    public String statement;


    public String phone;
    public String docNumber;
    public Date docDate;

    public byte isIdentified;

    public UserView() {

    }

    public UserView(String login, String password, String firstName, String secondName, String middleName, String statement,
                    String phone, String docNumber, Date docDate, byte isIdentified) {
        this.login=login;
        this.password=password;
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
