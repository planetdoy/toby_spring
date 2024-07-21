package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ObjectFactory {
    /*@Bean // 각각 하나의 빈을 만드는 메서들이기 때문에 @Bean을 붙여준다.
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    @Bean // 각각 하나의 빈을 만드는 메서들이기 때문에 @Bean을 붙여준다.
    public ExRateProvider exRateProvider() {
        return new SimpleExRateProvider();
    }*/
}
