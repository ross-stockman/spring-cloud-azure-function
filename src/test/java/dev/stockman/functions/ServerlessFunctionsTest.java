package dev.stockman.functions;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

public class ServerlessFunctionsTest {

    private final ServerlessFunctions systemUnderTest = new ServerlessFunctions();

    @Test
    void hello() {
        Mono<String> result = systemUnderTest.hello().apply(Mono.just("Kilroy"));
        assertThat(result.block()).isEqualTo("Kilroy was here!");
    }

    @Test
    void time() {
        Mono<String> result = systemUnderTest.time().get();
        assertThat(result.block()).isEqualTo("The time is very late!");
    }

    @Test
    void publish() {
        systemUnderTest.publish().accept(Mono.just("Foobar"));
    }
}