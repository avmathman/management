package com.invent.management.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Common mapper for converting model object(s) to entity object(s) and vice versa.
 */
public interface ModelMapper<ModelType, EntityType> {
    /**
     * Converts model object to entity object.
     *
     * @param model - The model object.
     * @return The entity object.
     */
    EntityType modelToEntity(ModelType model);

    /**
     * Converts entity object to model object.
     *
     * @param entity - The entity object.
     * @return The model object.
     */
    ModelType entityToModel(EntityType entity);

    /**
     * Converts a list of entities objects to a list of model objects.
     *
     * @param entities - The list of entity objects.
     * @return The list of model objects.
     */
    List<ModelType> entitiesToModels(List<EntityType> entities);

    /**
     * Converts a page of entities objects to a page of model objects.
     *
     * @param entities - The page of entity objects.
     * @param pageable - Paging and sorting information.
     * @return The page of model objects.
     */
    default Page<ModelType> entitiesToModels(Page<EntityType> entities, Pageable pageable) {
        return new PageImpl<>(entitiesToModels(entities.getContent()), pageable, entities.getTotalElements());
    }

    /**
     * Converts a list of models objects to a list of entity objects.
     *
     * @param models - The list of models objects.
     * @return The list of entity objects.
     */
    List<EntityType> modelsToEntities(List<ModelType> models);

    /**
     * Converts a page of models objects to a page of entity objects.
     *
     * @param models - The page of models objects.
     * @param pageable - Paging and sorting information.
     * @return The page of entity objects.
     */
    default Page<EntityType> modelsToEntities(Page<ModelType> models, Pageable pageable) {
        return new PageImpl<>(modelsToEntities(models.getContent()), pageable, models.getTotalElements());
    }
}
