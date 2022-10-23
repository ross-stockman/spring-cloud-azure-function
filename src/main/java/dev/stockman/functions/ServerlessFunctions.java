package dev.stockman.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.time.LocalTime;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class ServerlessFunctions {

    private static final Logger LOG = LoggerFactory.getLogger(ServerlessFunctions.class);

    @Bean
    public Function<Mono<String>, Mono<String>> hello() {
        return (name) -> name
                .doOnSuccess(m -> LOG.info("Received event >> " + m))
                .map(n -> String.format("%s was here!", n));
    }

    @Bean
    public Supplier<Mono<String>> time() {
        return () -> Mono.just("very late")
                .doOnSuccess(m -> LOG.info("Received event >> " + m))
                .map(m -> String.format("The time is %s!", m));
    }

    @Bean
    public Consumer<Mono<String>> publish() {
        return (message) -> message
                .doOnSuccess(m -> LOG.info("Received event >> " + m))
                .subscribe();
    }

}
