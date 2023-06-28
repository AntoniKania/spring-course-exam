package jaz.exam.nbp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("request")
public class Request {
    @Id
    @Size(min = 6, max = 20)
    @Schema(description = "Request id")
    private String id;
    @Size(min=3, max=3)
    @Schema(description = "Currency code")
    private String currency;
    @Schema(description = "Average currency rate returned")
    private Double average;
    @Schema(description = "Starting date of time window to calculate value for")
    private Date startDate;
    @Schema(description = "End date of time window to calculate value for")
    private Date endDate;
    @Schema(description = "Date of issuing the request")
    private Date requestDate;

    public Request(String currency, Double average, Date startDate, Date endDate, Date requestDate) {
        this.currency = currency;
        this.average = average;
        this.startDate = startDate;
        this.endDate = endDate;
        this.requestDate = requestDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
}
