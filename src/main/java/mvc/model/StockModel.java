package mvc.model;

import java.math.BigDecimal;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class StockModel {
    private String symbol;
    private double price;
    
    public StockModel(String symbol) {
        this.symbol = symbol;
        try {
            Stock stock = YahooFinance.get(symbol);
            this.price = stock.getQuote().getPrice().doubleValue();
        } catch (Exception e) {
        }
        
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
    
    
    
}
