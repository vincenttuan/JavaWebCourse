package twii;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_TWII")
public class TWII {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stockNo;
    private String stockName;
    private double yield;
    private double pe;
    private double pb;

    public TWII() {
    }
    
    public TWII(String stockNo, String stockName, double yield, double pe, double pb) {
        this.stockNo = stockNo;
        this.stockName = stockName;
        this.yield = yield;
        this.pe = pe;
        this.pb = pb;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockNo() {
        return stockNo;
    }

    public void setStockNo(String stockNo) {
        this.stockNo = stockNo;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getYield() {
        return yield;
    }

    public void setYield(double yield) {
        this.yield = yield;
    }

    public double getPe() {
        return pe;
    }

    public void setPe(double pe) {
        this.pe = pe;
    }

    public double getPb() {
        return pb;
    }

    public void setPb(double pb) {
        this.pb = pb;
    }

    @Override
    public String toString() {
        return "TWII{" + "id=" + id + ", stockNo=" + stockNo + ", stockName=" + stockName + ", yield=" + yield + ", pe=" + pe + ", pb=" + pb + '}';
    }
    
    
}
