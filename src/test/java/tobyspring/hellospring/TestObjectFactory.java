package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.exrate.WebApiExRateProvider;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.payment.ExRateProviderStub;
import tobyspring.hellospring.payment.PaymentService;

import java.math.BigDecimal;

@Configuration
//@ComponentScan
public class TestObjectFactory {
    @Bean // 각각 하나의 빈을 만드는 메서들이기 때문에 @Bean을 붙여준다.
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    @Bean // 각각 하나의 빈을 만드는 메서들이기 때문에 @Bean을 붙여준다.
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }

}