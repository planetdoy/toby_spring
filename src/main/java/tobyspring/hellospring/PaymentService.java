package tobyspring.hellospring;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

abstract public class PaymentService {
    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        // ~~ 환율 가져오기 ~~ -> 코드를 이해하는데 문제가 없다면 주석은 제거한다.
        BigDecimal exRate = getExRate(currency);

        // ~~ 금액 계산 ~~ -> 코드를 이해하는데 문제가 없다면 주석은 제거한다.
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);

        // ~~ 유효 시간 계산 ~~ -> 코드를 이해하는데 문제가 없다면 주석은 제거한다.
        LocalDateTime validUtil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUtil);
    }

    abstract BigDecimal getExRate(String currency) throws IOException;

}
