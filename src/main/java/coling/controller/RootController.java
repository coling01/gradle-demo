package coling.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@AllArgsConstructor
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @GetMapping("/")
    public ResponseEntity<String> helloWorld() {
        log.info("STARTED with traceId:{} spanId:{} authUserName:{}", MDC.get("traceId"), MDC.get("spanId"));
        // Normally we do stuff in here
        return ok("Hello world");
    }
}
