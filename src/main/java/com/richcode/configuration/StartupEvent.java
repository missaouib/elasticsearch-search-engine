package com.richcode.configuration;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.search.mapper.orm.Search;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
class StartupEvent implements ApplicationListener<ApplicationReadyEvent> {

    private final EntityManager em;

    @Override
    @Transactional
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        index();
    }

    private void index() {
        final long start = System.currentTimeMillis();
        Search
                .session(em.unwrap(Session.class))
            .massIndexer()
            .batchSizeToLoadObjects(100)
            .start()
            .thenRun(() -> log.info("Mass indexing succeeded in " + (System.currentTimeMillis() - start) + "ms"))
            .exceptionally(throwable -> {
                log.error("Mass indexing failed", throwable);
                return null;
            });
    }
}
