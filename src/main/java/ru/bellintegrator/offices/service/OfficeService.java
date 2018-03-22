package ru.bellintegrator.offices.service;

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
     * @return {@Office}
     */
    List<OfficeView> office();

    List<OfficeView> list(OfficeView office);

    OfficeView find(Long id);

    void update(OfficeView office);

    void delete(Long id);
}
