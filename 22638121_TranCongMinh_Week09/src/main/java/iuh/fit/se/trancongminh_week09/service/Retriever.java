package iuh.fit.se.trancongminh_week09.service;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class Retriever {
    public List<String> retrieve(String query) {
        return List.of(
                "Flight HA123 from Hanoi to Tokyo on 2025-11-05.",
                "Passengers can cancel their booking 24h before departure."
        );
    }
}
