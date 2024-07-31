package tobyspring.hellospring.payment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.TestObjectFactory;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
class PaymentServiceSpringTest {

    @Autowired
    PaymentService paymentService;
    @Autowired
    ExRateProviderStub exRateProviderStub;

    @Test
    void convertedAmount() throws IOException {
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율정보를 가져온다
        assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1_000));
        // 원화환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));


        exRateProviderStub.setExRate(valueOf(500));
        Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율정보를 가져온다
        assertThat(payment2.getExRate()).isEqualByComparingTo(valueOf(500));
        // 원화환산금액 계산
        assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(valueOf(5_000));

        // 원화환산금액 유효시간 계산
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
//        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));

    }
}