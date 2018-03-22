package ru.bellintegrator.catalog.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import javax.print.Doc;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocView {
    @ApiModelProperty(hidden = true)
    public Long id;

    public String code;

    public String name;

    public DocView() {

    }

    public DocView(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
