package guru.springframework.brewery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync
@SpringBootApplication
@ComponentScan("guru.springframework.brewery.web.mappers")
public class TsbbSfgBreweryApplication {

    public static void main(String[] args) {
        SpringApplication.run(TsbbSfgBreweryApplication.class, args);
    }

}
