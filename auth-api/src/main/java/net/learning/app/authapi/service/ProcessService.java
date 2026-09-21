package net.learning.app.authapi.service;

import lombok.RequiredArgsConstructor;
import net.learning.app.authapi.entity.ProcessingLog;
import net.learning.app.authapi.entity.User;
import net.learning.app.authapi.entity.dtos.TransformRequest;
import net.learning.app.authapi.entity.dtos.TransformResponse;
import net.learning.app.authapi.repository.ProcessRecordRepository;
import net.learning.app.authapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProcessService {

    private final RestClient serviceBRestClient;
    private final ProcessRecordRepository repository;
    private final UserRepository userRepository;
    @Value("${service.b.internal-token}")
    private String internalToken;

    public String process(UUID userId, String text) {

        TransformResponse response = serviceBRestClient.post()
                .uri("") // baseUrl already points to /api/transform
                .header("X-Internal-Token", internalToken)
                .body(new TransformRequest(text))
                .retrieve()
                .body(TransformResponse.class);

        String output = response != null ? response.result() : null;

        User user = userRepository.findById(userId);
        ProcessingLog record = new ProcessingLog();

        record.setUser(user);
        record.setInputText(text);
        record.setOutputText(output);
        record.setCreatedAt(Instant.now());
        repository.save(record);

        return output;
    }

}
