package ru.sberbank.optdemo1;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Quote implements Serializable {

    private String ticker;
    private Date date;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Long volume;
    private Double waprice;

    private Double maxInMonth;
    private Double maxInYear;

    public Quote(String ticker, Date date, Double open, Double high, Double low, Double close, Long volume, Double waprice) {
        this.ticker = ticker;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.waprice = waprice;
    }

    public Quote setMaxInMonth(Double maxInMonth) {
        this.maxInMonth = maxInMonth;
        return this;
    }

    public Quote setMaxInYear(Double maxInYear) {
        this.maxInYear = maxInYear;
        return this;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "ticker='" + ticker + '\'' +
                ", date=" + date +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                ", waprice=" + waprice +
                '}';
    }
}
