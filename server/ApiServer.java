import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "controllers")
public class ApiServer {

    public static void main(String[] args) {
        int port = 8080;
        SpringApplication.run(ApiServer.class, "--server.port=" + port);
    }
}
