package net.learning.app.authapi.controller;

import lombok.RequiredArgsConstructor;
import net.learning.app.authapi.entity.dtos.ProcessRequest;
import net.learning.app.authapi.entity.dtos.ProcessResponse;
import net.learning.app.authapi.service.ProcessService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProcessController {

    private final ProcessService processService;

    @PostMapping("/process")
    public ProcessResponse process(@RequestBody ProcessRequest request) {
        UUID userId = UUID.fromString(request.UUID());

        String result = processService.process(userId, request.text());
        return new ProcessResponse(result);
    }
}
