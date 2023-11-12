package es.deusto.ingenieria.sd.auctions.currency.server.localtest;

import es.deusto.ingenieria.sd.auctions.currency.remote.CurrencyExchangeService;

public class LocalTest {

public static void main(String[] args) {
	
	try {
	 float usdrate = CurrencyExchangeService.getInstance().getUSDRate();
	 System.out.println ("Local date and time: " + new java.util.Date() + " ****** USD rate: " + usdrate);
	 
	}  catch (Exception e) {			
		System.out.println("\t# Error: " + e.getMessage());
	} 
	}
}
