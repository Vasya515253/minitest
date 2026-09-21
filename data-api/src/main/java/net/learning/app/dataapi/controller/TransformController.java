package net.learning.app.dataapi.controller;

import net.learning.app.dataapi.entity.TransformRequest;
import net.learning.app.dataapi.entity.TransformResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransformController {

    @PostMapping("/transform")
    public TransformResponse transform(@RequestBody TransformRequest request) {
        String text = request.text() == null ? "" : request.text();
        String result = new StringBuilder(text).reverse().toString().toUpperCase();
        return new TransformResponse(result);
    }
}