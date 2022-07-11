package kg.peaksoft.ebookm1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class EbookM1Application {

	public static void main(String[] args) {
		SpringApplication.run(EbookM1Application.class, args);
		System.out.println("Hey, it's eBook-m1 project");
	}

	@GetMapping("/")
	public String greetingPage() {
		return "<h1>Welcome to eBook-m1 application!!!<h1/>";
	}
}
