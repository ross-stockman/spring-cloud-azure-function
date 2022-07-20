package dev.stockman.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class ServerlessFunctions {

    private static final Logger LOG = LoggerFactory.getLogger(ServerlessFunctions.class);

    @Bean
    public Function<String, String> hello() {
        return (name) -> name + " was here!";
    }

    @Bean
    public Supplier<String> time() {
        return () -> "The time is " + LocalTime.now();
    }

    @Bean
    public Consumer<String> publish() {
        return (message) -> LOG.info("Received event >> " + message);
    }

}
