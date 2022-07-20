package dev.stockman.functions;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

public class AzureTriggers extends FunctionInvoker<String, String> {

    @FunctionName("hello")
    public HttpResponseMessage hello(
            @HttpTrigger(name = "request", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<String> request,
            final ExecutionContext context) {

        return request.createResponseBuilder(HttpStatus.OK)
                .body(handleRequest(request.getBody(), context))
                .header("Content-Type", "application/json")
                .build();
    }

    @FunctionName("time")
    public HttpResponseMessage time(
            @HttpTrigger(name = "request", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<String> request,
            final ExecutionContext context) {

        context.getLogger().info("1.0 Testing attempt");

        return request.createResponseBuilder(HttpStatus.OK)
                .body(handleRequest(context))
                .header("Content-Type", "application/json")
                .build();
    }

    @FunctionName("publish")
    public HttpResponseMessage publish(
            @HttpTrigger(name = "request", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<String> request,
            final ExecutionContext context) {

        handleRequest(request.getBody(), context);

        return request.createResponseBuilder(HttpStatus.NO_CONTENT).build();
    }
}
