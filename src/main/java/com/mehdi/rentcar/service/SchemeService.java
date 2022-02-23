package com.mehdi.rentcar.service;

import com.mehdi.rentcar.model.dto.SchemeData;
import com.mehdi.rentcar.model.dto.response.PageResponse;

public interface SchemeService {

    SchemeData getDefaultSchemeByType(int type);

    PageResponse<SchemeData> getAllSchemes(Integer type, int page, int size, boolean enabled);

    SchemeData getSchemeById(long schemeId);

    SchemeData createScheme(SchemeData newScheme);

    SchemeData updateScheme(SchemeData updatedScheme, long schemeId);
}