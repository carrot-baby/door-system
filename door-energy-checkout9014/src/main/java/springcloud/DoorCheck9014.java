package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;
import springcloud.config.NodeRule275;

@SpringBootApplication
@EnableDiscoveryClient
public class DoorCheck9014 {
    public static void main(String[] args) {
        SpringApplication.run(DoorCheck9014.class,args);
    }
    @Configuration
    @RibbonClient(name = "door-energy-calculate" ,configuration = {NodeRule275.class})
    public class GoodsRibbonConfig {
    }



}
