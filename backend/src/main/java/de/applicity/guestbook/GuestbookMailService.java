package de.applicity.guestbook;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;

@Service
public class GuestbookMailService {

	@Autowired
	private GuestbookMailClient mailClient;
	
	@HystrixCommand(fallbackMethod = "defaultMail")
	public Future<Boolean> sendMail(final GuestbookEntry entry) {
		
		return new AsyncResult<Boolean>() {

			@Override
			public Boolean invoke() {
				System.out.println("+++ Sending mail +++");
				mailClient.sendMail(entry);
				System.out.println("+++ Sending mail: SUCCESS +++");
				
				return true;
			}
			
		};
		
	}
	
	private Boolean defaultMail(GuestbookEntry entry) {
		System.out.println("+++ Sending mail: FAILURE +++");
		
		return false;
	}
	
}
