package dev.stockman.functions;

import com.microsoft.azure.functions.ExecutionContext;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;


class AzureTriggersTest {

    private static final Logger LOGGER = Logger.getLogger(AzureTriggersTest.class.getName());

    private final FunctionInvoker<String, String> systemUnderTest = new FunctionInvoker<>(ServerlessFunctions.class);

    @Test
    void hello() {
        String result = systemUnderTest.handleRequest("Kilroy", new ExecutionContext() {
            @Override
            public Logger getLogger() {
                return LOGGER;
            }
            @Override
            public String getInvocationId() {
                return "id1";
            }
            @Override
            public String getFunctionName() {
                return "hello";
            }
        });
        systemUnderTest.close();
        assertThat(result).isEqualTo("Kilroy was here!");
    }

    @Test
    void time() {
        String result = systemUnderTest.handleRequest(new ExecutionContext() {
            @Override
            public Logger getLogger() {
                return LOGGER;
            }
            @Override
            public String getInvocationId() {
                return "id1";
            }
            @Override
            public String getFunctionName() {
                return "time";
            }
        });
        systemUnderTest.close();
        assertThat(result).isEqualTo("The time is very late!");
    }

    @Test
    void publish() {
        systemUnderTest.handleRequest("Foobar", new ExecutionContext() {
            @Override
            public Logger getLogger() {
                return LOGGER;
            }
            @Override
            public String getInvocationId() {
                return "id1";
            }
            @Override
            public String getFunctionName() {
                return "publish";
            }
        });
        systemUnderTest.close();
    }
}