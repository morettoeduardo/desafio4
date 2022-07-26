package br.com.saks.imobiliaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ImobiliariaDesafio04Application {

	public static void main(String[] args) {
		SpringApplication.run(ImobiliariaDesafio04Application.class, args);
	}

}
