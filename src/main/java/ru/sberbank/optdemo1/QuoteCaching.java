package ru.sberbank.optdemo1;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
public class QuoteCaching implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    public HistogramWriter histogram;

    private Logger log = LoggerFactory.getLogger(QuoteCaching.class);

    public static Map<Integer, List<Quote>> quotesMap = new HashMap<>();
    private static final String URL = "http://export.rbc.ru/free/selt.0/free.fcgi?period=DAILY&tickers=USD000000TOD&separator=TAB&data_format=BROWSER";

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        log.info("Start caching");
        AsyncHttpClient client = AsyncHttpClientFactory.create(new AsyncHttpClientFactory.AsyncHttpClientConfig());

        for (int i = 1; i < 101; i++) {
            System.out.println(i);
            Response response = null;
            try {
                response = client.prepareGet(URL + "&lastdays=" + i).execute().get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            String body = response.getResponseBody();

            String[] lines = body.split("\n");
            List<Quote> quotes = new ArrayList<>();

            for (String s : lines) {
                String[] line = s.split("\t");
                Double high = Double.parseDouble(line[3]);

                //Сделай Builder
                Quote quote = null;
                try {
                    quote = new Quote(line[0],
                            new SimpleDateFormat("yyyy-MM-dd").parse(line[1]),
                            Double.parseDouble(line[2]),
                            Double.parseDouble(line[3]),
                            Double.parseDouble(line[4]),
                            Double.parseDouble(line[5]),
                            Long.parseLong(line[6]),
                            Double.parseDouble(line[7]));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                quote.setMaxInMonth(high);
                quote.setMaxInYear(high);

                quotes.add(quote);
            }

            quotesMap.put(i, quotes);
        }

        log.info("Caching end");

        quotesMap.forEach((k,v) -> {
            log.info("key: " + k + " " + v.toString());
        });

        requestYourSelf(client, 1000);

        histogram.emit(new File("./myLog.txt"));
        return;
    }

    private void requestYourSelf(AsyncHttpClient client, int times) {
        for (int i = 0; i < times; i++) {
            log.info("Request: " + i);
            try (AutoCloseable ignored = histogram.wrap()){
                client.prepareGet("http://localhost:8080/demo1/quotes?days=30").execute().get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
