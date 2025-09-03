package coling.integration;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class RootControllerIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger(RootControllerIntegrationTest.class);

    @Resource
    MockMvc mockMvc;

    @Test
    void shouldCreateNewTraceId() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/")
                .header("trace-id", "123")
                .header("span-id", "456")
                .header("correlation-id", "789"))
                .andDo(print())
                .andReturn();
        assertThat(result.getResponse().getHeader("traceId")).hasSize(32);
        assertThat(result.getResponse().getHeader("spanId")).hasSize(16);
    }

    @Test
    void shouldUseExistingTraceIdFromHeader() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/")
                .header("trace-id", "123"));
    }
}
