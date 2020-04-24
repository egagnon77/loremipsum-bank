package bank;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication bankApplication = new SpringApplication(Application.class);
        bankApplication.setBannerMode(Banner.Mode.OFF);
        bankApplication.setLogStartupInfo(false);
        bankApplication.run(args);
    }
}
