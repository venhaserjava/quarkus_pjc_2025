package commons.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class JacksonConfig {

    @Inject
    ObjectMapper objectMapper;

    @PostConstruct
    void init() {
        objectMapper.registerModule(new JavaTimeModule());
    }
}
