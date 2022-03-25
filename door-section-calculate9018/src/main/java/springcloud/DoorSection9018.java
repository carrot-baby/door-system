package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;
import springcloud.config.NodeRule272;

@SpringBootApplication
@EnableDiscoveryClient
public class DoorSection9018 {
    public static void main(String[] args) {
        SpringApplication.run(DoorSection9018.class, args);
    }

    @Configuration
    @RibbonClient(name = "door-basic-parameter", configuration = {NodeRule272.class})
    public class GoodsRibbonConfig {
    }

}
