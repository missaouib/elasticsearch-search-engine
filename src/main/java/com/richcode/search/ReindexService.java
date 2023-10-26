package com.richcode.search;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.work.SearchIndexingPlan;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ReindexService {

    private final EntityManager em;

    public void update(final Class<?> clazz, final Object id) {
        Search.session(em)
                .indexingPlan()
                .addOrUpdate(em.getReference(clazz, id));
    }

    public void update(final Class<?> clazz, final Collection<?> ids) {
        SearchIndexingPlan indexingPlan = Search.session(em).indexingPlan();
        ids.forEach(id -> indexingPlan.addOrUpdate(em.getReference(clazz, id)));
    }

}
