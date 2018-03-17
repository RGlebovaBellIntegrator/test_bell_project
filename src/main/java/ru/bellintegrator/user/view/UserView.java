package ru.bellintegrator.user.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserView {
    @ApiModelProperty(hidden = true)
    public String id;

    public String login;

    public String password;

    public String name;

    public UserView() {

    }

    public UserView(String login, String password, String name) {
        this.login=login;
        this.password=password;
        this.name=name;
    }
}
