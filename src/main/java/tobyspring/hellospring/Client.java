package tobyspring.hellospring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.payment.Payment;
import tobyspring.hellospring.payment.PaymentService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment1 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("Payment: " + payment1);


        /**
         * 데코레이터 패턴 예시
         * */
//        Payment payment1 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
//        System.out.println(payment1);
//
//        System.out.println("--------------------------------------\n");
//
//        TimeUnit.SECONDS.sleep(1);
//
//        Payment payment2 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
//        System.out.println(payment2);
//
//        System.out.println("--------------------------------------\n");
//
//        TimeUnit.SECONDS.sleep(2);
//
//        Payment payment3 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
//        System.out.println(payment3);



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


    }
}
