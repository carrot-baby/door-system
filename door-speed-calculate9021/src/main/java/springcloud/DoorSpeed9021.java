package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;
import springcloud.config.NodeRule273;

@SpringBootApplication
@EnableDiscoveryClient
public class DoorSpeed9021 {
    public static void main(String[] args) {
        SpringApplication.run(DoorSpeed9021.class, args);
    }

    @Configuration
    @RibbonClients(
            {@RibbonClient(name = "door-section-calculate", configuration = {NodeRule273.class}),
                    @RibbonClient(name = "door-basic-parameter", configuration = {NodeRule273.class})
            }
    )
    public class GoodsRibbonConfig {
    }
}
