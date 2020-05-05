package com.matthewksc.billlogic;

import com.matthewksc.billlogic.Dao.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class BillLogicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillLogicApplication.class, args);
	}

}
