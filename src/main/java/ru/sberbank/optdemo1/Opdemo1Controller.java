package ru.sberbank.optdemo1;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/demo1")
public class Opdemo1Controller {

	private static final String URL = "http://export.rbc.ru/free/selt.0/free.fcgi?period=DAILY&tickers=USD000000TOD&separator=TAB&data_format=BROWSER";
	private static Map<Integer, List<Quote>> quotesMap = new HashMap<>();

	@RequestMapping("/quotes")
	public @ResponseBody
	List<Quote> quotes(@RequestParam("days") Integer days) throws ExecutionException, InterruptedException, ParseException {

		if (!quotesMap.containsKey(days)) {
			AsyncHttpClient client = AsyncHttpClientFactory.create(new AsyncHttpClientFactory.AsyncHttpClientConfig());
			Response response = client.prepareGet(URL + "&lastdays=" + days).execute().get();
			String body = response.getResponseBody();

			String[] lines = body.split("\n");
			List<Quote> quotes = new ArrayList<>();

			for (String s : lines) {
				String[] line = s.split("\t");
				Double high = Double.parseDouble(line[3]);

				//Сделай Builder
				Quote quote = new Quote(line[0],
						new SimpleDateFormat("yyyy-MM-dd").parse(line[1]),
						Double.parseDouble(line[2]),
						Double.parseDouble(line[3]),
						Double.parseDouble(line[4]),
						Double.parseDouble(line[5]),
						Long.parseLong(line[6]),
						Double.parseDouble(line[7]));
				quote.setMaxInMonth(high);
				quote.setMaxInYear(high);

				quotes.add(quote);
			}

			quotesMap.put(days, quotes);
		}

		return quotesMap.get(days);
	}

}

