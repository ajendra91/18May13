package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

    @Bean
    public RestTemplate demo() {
        RestTemplate template =new RestTemplate();
        template.getMessageConverters().add(new StringHttpMessageConverter());
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return template;
    }

}
