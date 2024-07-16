package tobyspring.hellospring;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class PaymentService {
    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        // 환율 가져오기
        // https://open.er-api.com/v6/latest/USD
        URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // Http 에 사용되는 기능을 사용하기 위해 타입 변경

        /**
         * 파일이나 네트워크를 통해서 넘겨오는 데이터를 바이트 형태로 변경 -> connection.getInputStream()
         * 캐릭터(문자)로 변경  -> new InputStreamReader(connection.getInputStream())
         * 사람이 알아 볼 수 있는 형태로 변경하기 위해 처리 -> new BufferedReader(new InputStreamReader(connection.getInputStream()))
         *
         * 자바 기초 : 다시 공부해 ㅅㅂㄻ
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = br.lines().collect(Collectors.joining());// 스트림 타입으로 BufferedReader 로 들어오는 값을 가져올 수 있다.
        br.close();

        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class);
        BigDecimal exRate = data.rates().get("KRW");

        // 금액 계산
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);

        // 유효 시간 계산
        LocalDateTime validUtil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUtil);
    };

    public static void main(String[] args) throws IOException {
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
