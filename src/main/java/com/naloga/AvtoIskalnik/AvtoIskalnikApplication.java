package com.naloga.AvtoIskalnik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AvtoIskalnikApplication {

	//private AvtoRepository repository;

	/*@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		List vsiAvti = this.repository.findAll();
		logger.info("Number of customers: " + vsiAvti.size());

		Avto newAvto = new Avto();
		newAvto.setFirstName("John");
		newCustomer.setLastName("Doe");
		logger.info("Saving new customer...");
		this.repository.save(newCustomer);

		allCustomers = this.repository.findAll();
		logger.info("Number of customers: " + allCustomers.size());
	} */
	public static void main(String[] args) {
		SpringApplication.run(AvtoIskalnikApplication.class, args);
	}

}
