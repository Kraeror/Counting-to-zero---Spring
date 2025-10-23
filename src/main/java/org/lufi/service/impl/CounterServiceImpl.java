package org.lufi.service.impl;

import org.lufi.service.CounterService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CounterServiceImpl implements CounterService {
    private final WebClient client;

    public CounterServiceImpl(WebClient.Builder webClientBuilder) {
        this.client = webClientBuilder
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Override
    public String countToZeroPing(String strNum) {
        try {
            int number = Integer.parseInt(strNum);
            if(number > 0)
            {
                int outputNumber = number - 1;
                System.out.println(outputNumber);
                return client.post()
                        .uri("/count-to-zero-pong?number={num}", String.valueOf(outputNumber) )
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
            }
        } catch (NumberFormatException e) {
            return "";
        }
        return "0";
    }

    @Override
    public String countToZeroPong(String strNum) {
        try {
            int number = Integer.parseInt(strNum);
            if(number > 0)
            {
                int outputNumber = number - 2;
                System.out.println(outputNumber);
                return client.post()
                        .uri("/count-to-zero-ping?number={num}", String.valueOf(outputNumber))
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
            }
        } catch (NumberFormatException e) {
            return "";
        }
        return "0";
    }
}
