package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.exrate.CachedExRateProvider;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.exrate.WebApiExRateProvider;
import tobyspring.hellospring.payment.PaymentService;

@Configuration
//@ComponentScan
public class ObjectFactory {
    @Bean // 각각 하나의 빈을 만드는 메서들이기 때문에 @Bean을 붙여준다.
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

//    @Bean // 각각 하나의 빈을 만드는 메서들이기 때문에 @Bean을 붙여준다.
//    public ExRateProvider cachedExRateProvider() {
//        return new CachedExRateProvider(exRateProvider());
//    }

    @Bean // 각각 하나의 빈을 만드는 메서들이기 때문에 @Bean을 붙여준다.
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }
}

class OrderService {
    ExRateProvider exRateProvider;

    public OrderService(ExRateProvider exRateProvider) {
        this.exRateProvider = exRateProvider;
    }

}