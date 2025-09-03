package coling.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Order(Ordered.LOWEST_PRECEDENCE)
@Component
public class ResponseFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(ResponseFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("ResponseFilter called for url:{}", request.getRequestURI());
        response.addHeader("traceId", MDC.get("traceId"));
        response.addHeader("spanId", MDC.get("spanId"));
        filterChain.doFilter(request, response);
    }
}