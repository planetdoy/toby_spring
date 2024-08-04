package tobyspring.hellospring.payment;


import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

//@Component
public class PaymentService {
    private final ExRateProvider exRateProvider;
    private final Clock clock;


    public PaymentService(ExRateProvider exRateProvider, Clock clock) {
        this.exRateProvider = exRateProvider;
        this.clock = clock;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {

        // ~~ 환율 가져오기 ~~ -> 코드를 이해하는데 문제가 없다면 주석은 제거한다.
        BigDecimal exRate = exRateProvider.getExRate(currency);

        // ~~ 금액 계산 ~~ -> 코드를 이해하는데 문제가 없다면 주석은 제거한다.
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);

        // ~~ 유효 시간 계산 ~~ -> 코드를 이해하는데 문제가 없다면 주석은 제거한다.
        LocalDateTime validUtil = LocalDateTime.now(clock).plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUtil);
    }

}
