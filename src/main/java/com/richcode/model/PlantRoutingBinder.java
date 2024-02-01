package com.richcode.model;

import com.richcode.domain.Plant;
import org.hibernate.search.mapper.pojo.bridge.RoutingBridge;
import org.hibernate.search.mapper.pojo.bridge.binding.RoutingBindingContext;
import org.hibernate.search.mapper.pojo.bridge.mapping.programmatic.RoutingBinder;
import org.hibernate.search.mapper.pojo.bridge.runtime.RoutingBridgeRouteContext;
import org.hibernate.search.mapper.pojo.route.DocumentRoutes;

import java.util.Arrays;
import java.util.List;

public class PlantRoutingBinder implements RoutingBinder {

    private static final String FIELD_TO_USE = "name";
    private static final List<String> FAMILIES_TO_IGNORE = Arrays.asList("rosaceae");

    @Override
    public void bind(RoutingBindingContext context) {
        context.dependencies().use(FIELD_TO_USE);

        context.bridge(Plant.class, new Bridge());
    }

    public static class Bridge implements RoutingBridge<Plant> {
        @Override
        public void route(
                DocumentRoutes routes,
                Object entityIdentifier,
                Plant indexedEntity,
                RoutingBridgeRouteContext context) {
            if (FAMILIES_TO_IGNORE.contains(indexedEntity.getFamily())) {
                routes.notIndexed();
            } else {
                routes.addRoute();
            }
        }

        @Override
        public void previousRoutes(
                DocumentRoutes routes,
                Object entityIdentifier,
                Plant indexedEntity,
                RoutingBridgeRouteContext context) {
            routes.addRoute();
        }
    }
}