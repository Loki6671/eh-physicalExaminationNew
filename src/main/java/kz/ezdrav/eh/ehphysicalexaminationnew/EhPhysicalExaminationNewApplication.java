package kz.ezdrav.eh.ehphysicalexaminationnew;

import kz.ezdrav.eh.ehphysicalexaminationnew.feign.RpnClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = {RpnClient.class})

public class EhPhysicalExaminationNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(EhPhysicalExaminationNewApplication.class, args);
	}

}
