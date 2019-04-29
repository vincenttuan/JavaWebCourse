package ctag;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class StockTag extends SimpleTagSupport{
    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        if(symbol == null || symbol.trim().equals("")) symbol = "2330.TW";
        Stock stock = YahooFinance.get(symbol);
        BigDecimal price = stock.getQuote().getPrice();        
        out.print(symbol + " price = " + price);
    }
    
    
}
