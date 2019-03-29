package jrest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Path("/exchange")
public class ExchangeRS {
    
    @GET
    @Path("/{symbol}")
    public double exchange(@PathParam("symbol") String symbol) {
        symbol = symbol.contains("=x")?symbol:symbol+"=x";
        try {
            Stock stock = YahooFinance.get(symbol);
            return stock.getQuote().getPrice().doubleValue();
        } catch (Exception e) {
        }
        return 0;
    }
//    
//    public static void main(String[] args) {
//        System.out.println(new ExchangeRS().exchange("USDTWD=x"));
//    }
    
}
