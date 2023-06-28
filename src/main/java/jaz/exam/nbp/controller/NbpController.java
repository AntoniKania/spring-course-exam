package jaz.exam.nbp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jaz.exam.nbp.service.NbpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ApiResponses(value = {
        @ApiResponse(
                responseCode = "400",
                description = "Bad request",
                content = @Content(schema = @Schema())
        ),
        @ApiResponse(
                responseCode = "502",
                description = "Internal Server Error",
                content = @Content(schema = @Schema())
        ),
        @ApiResponse(
                responseCode = "504",
                description = "Error on connection to external service",
                content = @Content(schema = @Schema())
        )
}
)
@RestController
@RequestMapping("nbp")
public class NbpController {
    private final NbpService nbpService;

    public NbpController(NbpService nbpService) {
        this.nbpService = nbpService;
    }

    @GetMapping("{currency}/{startDate}/{endDate}")
    @Operation(summary = "Get average currency rate",
            description = "Get average rate of the currency in given time window",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Average rate in given period",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Double.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Rates not found for given input",
                            content = @Content(schema = @Schema())
                    )
    })
    public ResponseEntity<Double> getAverageCurrencyRate(
            @Parameter(description = "Currency for which the average rates should be retrieved", required = true, example = "gbp") @PathVariable String currency,
            @Parameter(description = "Starting date of time window to calculate average value for", required = true, example = "2023-06-15") @PathVariable String startDate,
            @Parameter(description = "Ending date of time window to calculate average value for", required = true, example = "2023-06-20") @PathVariable String endDate
    ) {
        Double currencyRate = nbpService.getAverageCurrencyRate(currency, startDate, endDate);
        return ResponseEntity.ok(currencyRate);
    }
}
