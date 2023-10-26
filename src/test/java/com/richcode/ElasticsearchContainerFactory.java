package com.richcode;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ElasticsearchContainerFactory {

    private static final String ELASTICSEARCH_DOCKER_IMAGE = "docker.elastic.co/elasticsearch/elasticsearch:7.17.13";

    public static ElasticsearchContainer create() {
        ElasticsearchContainer container = new ElasticsearchContainer(ELASTICSEARCH_DOCKER_IMAGE)
            .withEnv("node.name", "es01")
            .withEnv("cluster.name", "rich-cluster")
            .withEnv("discovery.type", "single-node")
            .withEnv("xpack.security.enabled", "false");
        container.setPortBindings(List.of("9200:9200", "9300:9300"));
        container.setWaitStrategy(Wait
            .forHttp("/_cluster/health")
            .forPort(9200)
            .forStatusCode(200)
            .forResponsePredicate(res -> res.contains("yellow") || res.contains("green")));
        return container;
    }

}
