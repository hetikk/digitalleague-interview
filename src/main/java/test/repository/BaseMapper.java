package test.repository;

import org.springframework.beans.BeanUtils;
import test.model.Dto;

public abstract class BaseMapper<DTO extends Dto, ENTITY extends BaseEntity> {

    public abstract DTO toDto(ENTITY entity);

    public void fillEntity(DTO dto, ENTITY entity) {
        BeanUtils.copyProperties(dto, entity);
    }

}
