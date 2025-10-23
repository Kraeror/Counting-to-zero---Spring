
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lufi.service.impl.CounterServiceImpl;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CounterServiceImplTest {

    private CounterServiceImpl counterService;
    @Mock
    private WebClient webClient;
    @Mock
    private WebClient.Builder webClientBuilder;
    @Mock
    private WebClient.RequestBodyUriSpec uriSpec;
    @Mock
    private WebClient.RequestBodyUriSpec bodySpec;
    @Mock
    WebClient.ResponseSpec responseSpec;

    @BeforeEach
    void setUp() {
        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        counterService = new CounterServiceImpl(webClientBuilder);
    }

    @Test
    void testCountToZeroPing_WithPositive_ReturnsCorrectNumber() {
        when(webClient.post()).thenReturn(uriSpec);
        when(uriSpec.uri(anyString(), anyString())).thenReturn(bodySpec);
        when(bodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("10"));

        String result = counterService.countToZeroPing("11");

        assertEquals("10", result);
    }
    @Test
    void testCountToZeroPong_WithPositive_ReturnsCorrectNumber() {
        when(webClient.post()).thenReturn(uriSpec);
        when(uriSpec.uri(anyString(), anyString())).thenReturn(bodySpec);
        when(bodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("9"));

        String result = counterService.countToZeroPong("11");

        assertEquals("9", result);
    }

    @Test
    void testCountToZeroPing_WithZero_ReturnsEmpty() {
        String result = counterService.countToZeroPing("0");
        assertEquals("0", result);
    }

    @Test
    void testCountToZeroPing_WithNegative_ReturnsEmpty() {
        String result = counterService.countToZeroPing("-3");
        assertEquals("0", result);
    }

    @Test
    void testCountToZeroPong_WithZero_ReturnsEmpty() {
        String result = counterService.countToZeroPong("0");
        assertEquals("0", result);
    }

    @Test
    void testCountToZeroPong_WithNegative_ReturnsEmpty() {
        String result = counterService.countToZeroPong("-3");
        assertEquals("0", result);
    }
}