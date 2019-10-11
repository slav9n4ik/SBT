package ru.sberbank.optdemo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo1")
public class Opdemo1Controller {

	private final QuoteCaching quoteCaching;
	private Logger log = LoggerFactory.getLogger(QuoteCaching.class);

	@Autowired
	public Opdemo1Controller(QuoteCaching quoteCaching) {
		this.quoteCaching = quoteCaching;
	}

	@RequestMapping("/quotes")
	public @ResponseBody
	List<Quote> quotes(@RequestParam("days") Integer days){
		log.info("Request /demo1/quotes?days=  SUCCESS");
		return quoteCaching.quotesMap.get(days);
	}

}

