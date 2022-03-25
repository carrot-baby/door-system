package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DoorBasic9002 {
    public static void main(String[] args) {
        SpringApplication.run(DoorBasic9002.class,args);
    }
//    @Configuration
//    @RibbonClient(name = "door-energy-calculate" ,configuration = {RandRule.class})
//    public class GoodsRibbonConfig {
//    }


}
