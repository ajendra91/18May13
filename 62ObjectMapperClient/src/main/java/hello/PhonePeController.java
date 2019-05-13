package hello;

import java.io.IOException;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class PhonePeController {

	@Autowired
	public RestTemplate template;
	
	ObjectMapper mapper=new ObjectMapper();
	
	public String url1 ="http://localhost:9090/pay/";
	public String url2="http://localhost:9090/vendor/";
	
	@PostMapping("/pay")
	public PaymentResponse pay2(@RequestBody Payment payment) throws IOException {

		String jsonreq =mapper.writeValueAsString(payment);
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity =new HttpEntity<String>(jsonreq,headers);
		//String result = template.exchange(url1, HttpMethod.POST, entity, String.class);
		String result = template.postForObject(url1, entity, String.class);
		return mapper.readValue(result, PaymentResponse.class);

	}
	
	@GetMapping("/vendor/{vendor}")
	public PaymentResponse getven2(@PathVariable String vendor) throws IOException {
		String result = template.getForObject(url2+vendor, String.class);
		return mapper.readValue(result, PaymentResponse.class);
	}
	
}
