package com.mehdi.rentcar.service.impl;

import com.mehdi.rentcar.exception.InputValidationException;
import com.mehdi.rentcar.exception.SchemeNotFoundException;
import com.mehdi.rentcar.mapper.Mapper;
import com.mehdi.rentcar.model.Scheme;
import com.mehdi.rentcar.model.dto.SchemeData;
import com.mehdi.rentcar.model.dto.response.PageResponse;
import com.mehdi.rentcar.repository.SchemeRepository;
import com.mehdi.rentcar.service.SchemeService;
import com.mehdi.rentcar.util.ConstantsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SchemeServiceImpl implements SchemeService {

    private final SchemeRepository schemeRepository;
    private final Mapper mapper;

    public SchemeServiceImpl(SchemeRepository schemeRepository, Mapper mapper) {
        this.schemeRepository = schemeRepository;
        this.mapper = mapper;
    }

    @Override
    public SchemeData getDefaultSchemeByType(int type) {
        return mapper.schemeEntityToData(schemeRepository.findByTypeAndDefaultItemIsTrue(type));
    }

    @Override
    public PageResponse<SchemeData> getAllSchemes(Integer type, int page, int size, boolean status) {
        Pageable paging = PageRequest.of(page, size);

        List<String> errors = validateType(type);

        if (!errors.isEmpty()) {
            throw new InputValidationException(errors);
        }
        Page<Scheme> pageScheme = schemeRepository.findSchemesByTypeAndStatus(type, status, paging);

        List<SchemeData> schemes = pageScheme.getContent().stream()
                .map(mapper::schemeEntityToData)
                .collect(Collectors.toList());

        return new PageResponse<>(schemes, pageScheme.getNumber(),
                pageScheme.getTotalElements(),
                pageScheme.getTotalPages());

    }

    private List<String> validateType(Integer type) {
        List<String> errors = new ArrayList<>();
        if (type == null || type <= 0) {
            errors.add("Invalid Type");
        } else {
            if (ConstantsUtils.SchemeType.getById(type) == null) {
                errors.add("Invalid Type");
            }
        }
        return errors;
    }

    @Override
    public SchemeData getSchemeById(long schemeId) {
        Optional<Scheme> schemeOptional = schemeRepository.findById(schemeId);

        if (schemeOptional.isEmpty()) {
            throw new SchemeNotFoundException("Scheme not found, schemeId : " + schemeId);
        }
        return mapper.schemeEntityToData(schemeOptional.get());
    }

    @Override
    public SchemeData createScheme(SchemeData newScheme) {
        newScheme.setId(0);
        List<String> errors = validateType(newScheme.getType());
        Scheme defaultScheme = null;
        if (!errors.isEmpty()) {
            throw new InputValidationException(errors);
        }
        if (newScheme.isDefaultItem()) {
            defaultScheme = schemeRepository.findByTypeAndDefaultItemIsTrue(newScheme.getType());
        }
        Scheme scheme = schemeRepository.save(mapper.schemeDataToEntity(newScheme));
        if (defaultScheme != null) {
            defaultScheme.setDefaultItem(false);
            schemeRepository.save(defaultScheme);
        }
        newScheme.setId(scheme.getId());
        return newScheme;
    }


    @Override
    public SchemeData updateScheme(SchemeData updatedScheme, long schemeId) {
        Optional<Scheme> schemeOptional = schemeRepository.findById(schemeId);

        Scheme defaultScheme = null;
        if (schemeOptional.isEmpty()) {
            throw new SchemeNotFoundException("Scheme not found, schemeId : " + schemeId);
        }
        Scheme scheme = schemeOptional.get();
        List<String> errors = validateUpdate(updatedScheme, scheme);
        if (!errors.isEmpty()) {
            throw new InputValidationException(errors);
        }
        if (updatedScheme.isDefaultItem()) {
            defaultScheme = schemeRepository.findByTypeAndDefaultItemIsTrue(updatedScheme.getType());
        }
        updateEntity(scheme, updatedScheme);
        Scheme savedScheme = schemeRepository.save(scheme);
        if (defaultScheme != null) {
            defaultScheme.setDefaultItem(false);
            schemeRepository.save(defaultScheme);
        }
        return mapper.schemeEntityToData(savedScheme);

    }

    private List<String> validateUpdate(SchemeData updatedScheme, Scheme scheme) {
        List<String> errors = new ArrayList<>();
        if (updatedScheme.getType() != scheme.getType()) {
            errors.add("Scheme Type cannot be updated");
        }
        if (scheme.isDefaultItem() && !updatedScheme.isDefaultItem()) {
            errors.add("You can't update default value, at least one default item");
        }
        return errors;
    }

    private void updateEntity(Scheme scheme, SchemeData updatedScheme) {
        scheme.setStatus(updatedScheme.isStatus());
        scheme.setName(updatedScheme.getName());
        scheme.setDescription(updatedScheme.getDescription());
        scheme.setDefaultItem(updatedScheme.isDefaultItem());
    }

}
