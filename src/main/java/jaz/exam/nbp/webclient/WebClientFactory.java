package jaz.exam.nbp.webclient;

import jaz.exam.nbp.exception.BadRequestException;
import jaz.exam.nbp.exception.InternalServerErrorException;
import jaz.exam.nbp.exception.NotFoundRateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientFactory {
    public static WebClient getWebClientWithErrorHandling() {
        return WebClient.builder()
                .baseUrl("http://api.nbp.pl")
                .filter(errorHandler())
                .build();
    }

    private static ExchangeFilterFunction errorHandler() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().is5xxServerError()) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(errorBody -> Mono.error(new InternalServerErrorException(errorBody)));
            } else if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(errorBody -> Mono.error(new NotFoundRateException(errorBody)));
            } else if (clientResponse.statusCode().equals(HttpStatus.BAD_REQUEST)) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(errorBody -> Mono.error(new BadRequestException(errorBody)));
            } else {
                return Mono.just(clientResponse);
            }
        });
    }
}
