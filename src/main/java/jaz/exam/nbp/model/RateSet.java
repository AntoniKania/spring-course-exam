package jaz.exam.nbp.model;

import java.util.Set;

public class RateSet {
    Set<Rate> rates;

    public RateSet() {
    }

    public RateSet(Set<Rate> rates) {
        this.rates = rates;
    }

    public Set<Rate> getRates() {
        return rates;
    }

    public void setRates(Set<Rate> rates) {
        this.rates = rates;
    }
}
