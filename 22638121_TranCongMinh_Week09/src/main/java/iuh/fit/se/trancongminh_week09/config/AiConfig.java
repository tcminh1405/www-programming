package iuh.fit.se.trancongminh_week09.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    public String geminiApiKey() {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("GEMINI_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("❌ Missing GEMINI_API_KEY in .env file");
        }
        System.out.println("✅ Loaded GEMINI_API_KEY successfully!");
        return apiKey;
    }
}
