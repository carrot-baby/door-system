package springcloud;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;
import springcloud.config.NodeRule8080;

@SpringBootApplication
@EnableDiscoveryClient

public class DoorWeb8080 {
    public static void main(String[] args) {
        SpringApplication.run(DoorWeb8080.class, args);
    }

    @Configuration
    @RibbonClient(name = "door-energy-checkout", configuration = NodeRule8080.class)
    public class TestConfiguration {
    }
}
