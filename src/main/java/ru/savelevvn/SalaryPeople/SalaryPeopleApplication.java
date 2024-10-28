package ru.savelevvn.SalaryPeople;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.savelevvn.SalaryPeople.HTMLParser.HTMLParser;
import ru.savelevvn.SalaryPeople.controller.PeopleController;

@SpringBootApplication
public class SalaryPeopleApplication {


	public static void main(String[] args) {
		SpringApplication.run(SalaryPeopleApplication.class, args);

	}

}
