package ru.bellintegrator.offices.controller;

import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.offices.view.OfficeView;

import java.util.List;

public interface OfficesController {
    /**
     * Add offices
     * @param offices
     */
    void offices(@RequestBody OfficeView offices);

    /**
     * Get all offices
     * @return JSON offices value
     */
    List<OfficeView> offices();
}
