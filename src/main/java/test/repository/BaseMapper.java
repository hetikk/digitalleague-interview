package test.repository;

import org.springframework.beans.BeanUtils;
import test.model.Dto;

/**
 * Class that implements mapping DTO to Entity classes and back
 */
public abstract class BaseMapper<DTO extends Dto, ENTITY extends BaseEntity> {

    /**
     * Convert entity class to DTO class
     * @param entity entity class to convert
     * @return DTO of entity class
     */
    public abstract DTO toDto(ENTITY entity);

    /**
     * Fill data from DTO to entity class
     */
    public void fillEntity(DTO dto, ENTITY entity) {
        BeanUtils.copyProperties(dto, entity);
    }

}
