package ritdatabaseproject.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;



@ComponentScan(basePackages = "ritdatabaseproject.business.controllers")
@SpringBootApplication
class ApiServer implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
         int port = 22222;
        SpringApplication.run(ApiServer.class, "--server.port="+port);
        Scanner scan = new Scanner(System.in);
        System.out.println("\n Press Any Key To Stop Spring Server...");
        String exit = null;
        
        while(exit == null)
        {
            exit = scan.nextLine();
        }
        System.exit(0);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            int port = applicationContext.getBean(Environment.class).getProperty("server.port", Integer.class, 8080);
            System.out.printf("%s:%d", ip, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}