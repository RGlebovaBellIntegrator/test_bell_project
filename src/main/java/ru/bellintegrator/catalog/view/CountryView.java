package ru.bellintegrator.catalog.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import javax.print.Doc;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryView {
    @ApiModelProperty(hidden = true)
    public int id;

    public int code;

    public String name;

    public CountryView() {

    }

    public CountryView(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
