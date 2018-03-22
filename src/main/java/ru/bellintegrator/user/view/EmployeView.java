package ru.bellintegrator.user.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeView {
    @ApiModelProperty(hidden = true)
    public Long id;

    public String fullName;

    public String firstName;

    public String secondName;

    public String middleName;
    public String position;


    public String phone;
    public String docCode;
    public String docName;
    public String docNumber;
    public Date docDate;
    public String citizenshipCode;
    public String citizenshipName;

    public Long officeId;

    public String officeName;

    public Boolean isIdentified;

    public EmployeView() {

    }

    public EmployeView(String firstName, String secondName, String middleName, String position,
                       String phone, String docNumber, Date docDate, Long officeId, Boolean isIdentified) {
        this.firstName=firstName;
        this.secondName=secondName;
        this.middleName=middleName;
        this.position = position;
        this.phone = phone;
        this.docNumber=docNumber;
        this.docDate = docDate;
        this.officeId = officeId;
        this.isIdentified = isIdentified;
    }

}
