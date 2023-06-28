package jaz.exam.nbp.service;

import jaz.exam.nbp.exception.BadRequestException;
import jaz.exam.nbp.exception.InternalServerErrorException;
import jaz.exam.nbp.exception.NotFoundRateException;
import jaz.exam.nbp.model.Rate;
import jaz.exam.nbp.model.Request;
import jaz.exam.nbp.repository.RequestRepository;
import jaz.exam.nbp.model.RateSet;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.ConnectException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.OptionalDouble;

@Controller
public class NbpService {
    private final WebClient webClient;
    private final RequestRepository requestRepository;

    public NbpService(WebClient webClient, RequestRepository requestRepository) {
        this.webClient = webClient;
        this.requestRepository = requestRepository;
    }

    public Double getAverageCurrencyRate(String currency, String startDateString, String endDateString) {
        Date startDate = convertToDateAndValidate(startDateString);
        Date endDate = convertToDateAndValidate(endDateString);

        String uri = String.format("api/exchangerates/rates/a/%s/%s/%s", currency, startDateString, endDateString);
        RateSet rateSet = webClient.method(HttpMethod.GET)
                .uri(uri)
                .header("Accept", "*/*")
                .retrieve()
                .bodyToMono(RateSet.class)
                .onErrorMap(e -> new ConnectException(e.getMessage()))
                .block();

        if (rateSet == null || rateSet.getRates().isEmpty()) {
            throw new NotFoundRateException("couldn't find rateSet for currency: " + currency + " for given period");
        }

        OptionalDouble average = rateSet.getRates().stream()
                .mapToDouble(Rate::getMid)
                .average();

        if (average.isEmpty()) {
            throw new InternalServerErrorException("Error occurred during rates processing.");
        }

        requestRepository.save(new Request(currency, average.getAsDouble(), startDate, endDate, new Date()));

        return average.getAsDouble();
    }

    private Date convertToDateAndValidate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            throw new BadRequestException("Date given in wrong format");
        }
    }

}
