package br.com.gabrieldeoliveira.dsmeta.services;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import br.com.gabrieldeoliveira.dsmeta.entities.Sale;
import br.com.gabrieldeoliveira.dsmeta.repositories.SaleRepository;

@Service
public class SMSService {

	@Value("${twilio.sid}")
	private String twilioSid;
	
	@Value("${twilio.key}")
	private String twilioKey;
	
	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;
	
	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;
	
	@Autowired
	private SaleRepository saleRepository;
	
	public void sendSMS(Long id) {
		Sale sale = saleRepository.findById(id).get();
		
		String date = sale.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String msg = "O Vendedor " + sale.getSellerName() + " vendeu em " + date
				+ " um total de R$ " + String.format("%.2f", sale.getAmount()) + ".";
		
		Twilio.init(twilioSid, twilioKey);
		
		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);
		
		Message message = Message.creator(to, from, msg).create();
		
		System.out.println(message.getSid());
	}
}
