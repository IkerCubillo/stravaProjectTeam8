package es.deusto.ingenieria.sd.sms.server.gateway;

public class MailSenderTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new MailSender(args[0]).sendMessage(args[1]);

	}

}
