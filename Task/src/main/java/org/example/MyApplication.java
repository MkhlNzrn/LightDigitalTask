package org.example;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MyApplication {
    private final Flyway flyway;

    public MyApplication(Flyway flyway) {
        this.flyway = flyway;
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
    @EventListener(ApplicationReadyEvent.class)
    public void runFlywayMigrations() {
        flyway.migrate();
    }
}

