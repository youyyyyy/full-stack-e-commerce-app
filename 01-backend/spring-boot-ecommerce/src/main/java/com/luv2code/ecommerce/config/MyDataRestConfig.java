package com.luv2code.ecommerce.config;

import com.luv2code.ecommerce.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;

    private EntityManager entityManager;

    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.PATCH, HttpMethod.DELETE};

        // disable HTTP methods for Product: PUT, POST, DELETE, PATCH
        // status code: 405 Method Not Allowed
        disableHttpMethod(Product.class, config, theUnsupportedActions);

        // disable HTTP methods for ProductCategory: PUT, POST, DELETE, PATCH
        disableHttpMethod(ProductCategory.class, config, theUnsupportedActions);

        // disable HTTP methods for Country: PUT, POST, DELETE, PATCH
        disableHttpMethod(Country.class, config, theUnsupportedActions);

        // disable HTTP methods for State: PUT, POST, DELETE, PATCH
        disableHttpMethod(State.class, config, theUnsupportedActions);

        // disable HTTP methods for Order: PUT, POST, DELETE, PATCH
        disableHttpMethod(Order.class, config, theUnsupportedActions);

        // call an internal helper method
        exposeIds(config);

        // configure the cors mapping
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);
    }

    private static void disableHttpMethod(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        // get a list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // create an array of the entity types
        List<Class> entityClasses = new ArrayList<>();

        // get the entity types for the entities
        for (EntityType tempEntityType : entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }

        // expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
