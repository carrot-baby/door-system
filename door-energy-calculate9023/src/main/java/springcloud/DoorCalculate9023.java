package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;
import springcloud.config.NodeRule274;

@SpringBootApplication
@EnableDiscoveryClient
public class DoorCalculate9023 {
    public static void main(String[] args) {
        SpringApplication.run(DoorCalculate9023.class, args);
    }

    @Configuration
    @RibbonClients(
            {@RibbonClient(name = "door-speed-calculate", configuration = {NodeRule274.class}),
                    @RibbonClient(name = "door-basic-parameter", configuration = {NodeRule274.class})
            }
    )
    public class GoodsRibbonConfig {
    }
}
