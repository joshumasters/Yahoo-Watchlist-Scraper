import java.io.Serializable;

public class StockEntry implements Serializable {
    String symbol;
    String companyName;
    String lastPrice;
    String change;
    String changePercent;
    String marketTime;
    String volume;
    String avgVol;
    String marketCap;

    

    public StockEntry(String symbol, String companyName, String lastPrice, String change, String changePercent,
            String marketTime, String volume, String avgVol, String marketCap) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.lastPrice = lastPrice;
        this.change = change;
        this.changePercent = changePercent;
        this.marketTime = marketTime;
        this.volume = volume;
        this.avgVol = avgVol;
        this.marketCap = marketCap;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getLastPrice() {
        return lastPrice;
    }
    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }
    public String getChange() {
        return change;
    }
    public void setChange(String change) {
        this.change = change;
    }
    public String getChangePercent() {
        return changePercent;
    }
    public void setChangePercent(String changePercent) {
        this.changePercent = changePercent;
    }
    public String getMarketTime() {
        return marketTime;
    }
    public void setMarketTime(String marketTime) {
        this.marketTime = marketTime;
    }
    public String getVolume() {
        return volume;
    }
    public void setVolume(String volume) {
        this.volume = volume;
    }
    public String getAvgVol() {
        return avgVol;
    }
    public void setAvgVol(String avgVol) {
        this.avgVol = avgVol;
    }
    public String getMarketCap() {
        return marketCap;
    }
    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }
    @Override
    public String toString() {
        return "StockEntry [avgVol=" + avgVol + ", change=" + change + ", changePercent=" + changePercent
                + ", companyName=" + companyName + ", lastPrice=" + lastPrice + ", marketCap=" + marketCap
                + ", marketTime=" + marketTime + ", symbol=" + symbol + ", volume=" + volume + "]";
    }
    
}
