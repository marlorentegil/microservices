package com.aula.microservices.users.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class LogCorrelationFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(LogCorrelationFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        // Leemos la cabecera que puso el Gateway
        String correlationId = req.getHeader("X-Correlation-Id");

        // Imprimimos el log para verificar
        log.info("ID de Trazabilidad recibido: [{}] para la ruta: {}", correlationId, req.getRequestURI());

        chain.doFilter(request, response);
    }
}