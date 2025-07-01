
package com.example.booking;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookingController {

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public String createBooking(@RequestBody Map<String, String> booking) {
        String kongBaseUrl = "http://kong-proxy.kong:80";

        restTemplate.postForObject(kongBaseUrl + "/payment/pay", booking, String.class);
        restTemplate.postForObject(kongBaseUrl + "/notify/send", booking, String.class);

        return "Booking created and services triggered.";
    }
}
