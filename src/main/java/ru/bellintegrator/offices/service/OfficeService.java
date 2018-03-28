package ru.bellintegrator.offices.service;

import ru.bellintegrator.offices.model.Office;
import ru.bellintegrator.offices.view.OfficeView;

import java.util.List;

public interface OfficeService {

    /**
     *
     * @param office
     */
    OfficeView add(OfficeView office);

    /**
     * Office service method
     * @return List<OfficeView>
     */
    List<OfficeView> office();

    List<OfficeView> list(OfficeView office);

    OfficeView find(Long id);

    void update(OfficeView office);

    void delete(Long id);
}
