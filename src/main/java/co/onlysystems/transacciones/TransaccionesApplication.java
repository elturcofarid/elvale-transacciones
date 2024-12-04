package co.onlysystems.transacciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"co.onlysystems.transacciones" })
public class TransaccionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransaccionesApplication.class, args);
	}

}
