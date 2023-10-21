package com.market.tracker.util.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    private final ModelMapper modelMapper;

    // Constructor with Dependency Injection
    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Converts an entity to a DTO.
     *
     * @param entity   The source entity object to be converted.
     * @param dtoClass The target DTO class.
     * @param <T>      The type of the entity.
     * @param <D>      The type of the DTO.
     * @return The DTO object after conversion.
     */
    public <T, D> D convertToDTO(T entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    /**
     * Converts a DTO to an entity.
     *
     * @param dto         The source DTO object to be converted.
     * @param entityClass The target entity class.
     * @param <T>         The type of the entity.
     * @param <D>         The type of the DTO.
     * @return The entity object after conversion.
     */
    public <T, D> T convertToEntity(D dto, Class<T> entityClass) {
        return modelMapper.map(dto, entityClass);
    }
}
