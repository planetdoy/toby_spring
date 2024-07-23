package tobyspring.hellospring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) throws IOException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        OrderService orderService = beanFactory.getBean(OrderService.class);
        System.out.println(paymentService.exRateProvider == orderService.exRateProvider);


        /**
         * new 를 통해서 오브젝트를 생성했지만 아래의 두 오브젝트는 동일하다.
         * @Configuration 의 영향으로 빈 객체는 싱글톤으로 생성됩니다.
         */
//        ObjectFactory objectFactory = beanFactory.getBean(ObjectFactory.class);
//        PaymentService paymentService1 = objectFactory.paymentService();// 팩토리 메서드
//        PaymentService paymentService2 = objectFactory.paymentService();// 팩토리 메서드
//        System.out.println(paymentService1 == paymentService2);

        /**
         * 스프링의 싱글톤 레지스트리 증명
         */
//        PaymentService paymentService2 = beanFactory.getBean(PaymentService.class);
//        System.out.println(paymentService);
//        System.out.println(paymentService2);
//        System.out.println(paymentService == paymentService2);

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
