package tobyspring.hellospring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true) // 없는 값 무시
public record ExRateData(String result, Map<String, BigDecimal> rates) {
}
