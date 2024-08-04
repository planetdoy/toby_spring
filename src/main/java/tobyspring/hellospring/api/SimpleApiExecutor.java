package tobyspring.hellospring.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.stream.Collectors;

public class SimpleApiExecutor implements ApiExecutor{
    @Override
    public String execute(URI uri) throws IOException {
        String response;
        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection(); // Http 에 사용되는 기능을 사용하기 위해 타입 변경

        try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            response = br.lines().collect(Collectors.joining());// 스트림 타입으로 BufferedReader 로 들어오는 값을 가져올 수 있다.
        }
        return response;
    }
}
