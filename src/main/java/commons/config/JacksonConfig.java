package commons.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.quarkus.jackson.ObjectMapperCustomizer;
import jakarta.inject.Singleton;

@Singleton
public class JacksonConfig implements ObjectMapperCustomizer {

    @Override
    public void customize(ObjectMapper objectMapper) {
        // Registrar o módulo para suportar Java Time
        objectMapper.registerModule(new JavaTimeModule());
    }
}

/*
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.quarkus.jackson.ObjectMapperCustomizer;
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

*/

