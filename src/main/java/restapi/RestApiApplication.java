package restapi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import restapi.config.AppConfig;

public class RestApiApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Communication communication = context.getBean("communication", Communication.class);
        System.out.println(communication.getOperation());
    }
}
