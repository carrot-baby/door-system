package common;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRobin {
    @Bean
    public IRule myRule() {
        return new RoundRobinRule();
    }
}
