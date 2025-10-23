package org.lufi.controller;

import lombok.RequiredArgsConstructor;
import org.lufi.service.CounterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CounterController {
    private final CounterService counterService;
    @PostMapping("/count-to-zero-ping")
    public ResponseEntity<String> countToZeroPing(@RequestParam String number) {
        String result = this.counterService.countToZeroPing(number);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/count-to-zero-pong")
    public ResponseEntity<String> countToZeroPong(@RequestParam String number) {
        String result = this.counterService.countToZeroPong(number);
        return ResponseEntity.ok(result);
    }
}
