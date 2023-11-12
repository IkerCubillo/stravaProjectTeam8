package es.deusto.ingenieria.sd.auctions.currency.remote;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrencyExchangeService extends UnicastRemoteObject implements ICurrencyExchange {
	private static final long serialVersionUID = 1L;

	protected static final String URL = "https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_GSSmdxlYgqDk4UNwIqhMzSb0xI4gSLPnUyNUjFhY&currencies=GBP%2CUSD&base_currency=EUR";
	//protected static final String URL = "https://free.currconv.com/api/v7/convert?q=EUR_USD,EUR_GBP&compact=ultra&apiKey=38fdb5e3f36145f9c59c";
	public static float USD_RATE = 1.00f;
	public static float GBP_RATE = 0.85f;
	
	//Attribute for the Singleton pattern
	public static CurrencyExchangeService instance;
			
	private CurrencyExchangeService() throws RemoteException {
		super();
		getConversionRates();
	}
	
	public static CurrencyExchangeService getInstance() {
		if (instance == null) {
			try {
				instance = new CurrencyExchangeService();
			} catch(Exception ex) {
				System.err.println("  # Error initializing service(): " + ex.getMessage());
			}
		}
		
		return instance;
	}

	private static final void getConversionRates() {
		System.out.println(" - Getting exchange rates from 'free.currconv.com'....");
		
		try {			
			HttpURLConnection con = (HttpURLConnection) (new URL(URL).openConnection());			
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			con.disconnect();

			inputLine = response.toString();
			System.out.println("Response from Free Currency server: " + inputLine);
			
						
			/** IF USING https://free.currconv.com/api, UNCOMMENT THE TWO LINES BELOW AND COMMENT THE PARSING CALL: */ 
			
			//USD_RATE = Float.parseFloat(inputLine.substring(inputLine.indexOf(":")+1, inputLine.indexOf(",")));
			//GBP_RATE = Float.parseFloat(inputLine.substring(inputLine.lastIndexOf(":")+1, inputLine.indexOf("}")));
			
			/** IF USING https://api.freecurrencyapi.com, call the parsing method - base currency EUR*/
			CurrencyExchangeService.parsingInputLine(inputLine);
			
		} catch(Exception ex) {
			System.out.println("  # Error getting conversion rates(): " + ex.getMessage());
		}
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/YYYY - HH:mm");
		
		
		System.out.println(" - Exchange rates obtained (" + dateFormatter.format(Calendar.getInstance().getTime()) + ")!!");
		System.out.println("\t- EURO to USD rate: " + USD_RATE);
		System.out.println("\t- EURO to GBP rate: " + GBP_RATE);
	}
	
	public float getUSDRate() throws RemoteException {
		getConversionRates();
		return USD_RATE;
	}

	public float getGBPRate() throws RemoteException {
		getConversionRates();
		return GBP_RATE;
	}
	
	private static void parsingInputLine(String inputLine) {
		// Find the position of "GBP" and "USD" values
        int gbpStart = inputLine.indexOf("\"GBP\":") + 6;
        int usdStart = inputLine.indexOf("\"USD\":") + 6;
        int gbpEnd = inputLine.indexOf(",", gbpStart);
        int usdEnd = inputLine.indexOf("}", usdStart);

        // Extract the substrings containing the values
        String gbpValueStr = inputLine.substring(gbpStart, gbpEnd);
        String usdValueStr = inputLine.substring(usdStart, usdEnd);
        
        GBP_RATE = Float.parseFloat(gbpValueStr);
        USD_RATE = Float.parseFloat(usdValueStr);

		
	}
}