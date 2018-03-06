package ru.bellintegrator.offices.controller;

import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.offices.view.OfficeView;

import java.util.List;

public interface OfficeController {
    /**
     * Add office
     * @param office
     */
    void office(@RequestBody OfficeView office);

    /**
     * Get all offices
     * @return JSON offices value
     */
    List<OfficeView> office();
}
