/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpmorgan;

import static Interfaces.UI_Step_1_1_1.listStock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 *
 * @author mason
 */
public class Stock {

    private String name;
    private String type;
    private double last_dividen;
    private double fixed_dividen;
    private double par_value;
    private double ticker_price;
    private LocalDateTime timestamp;
    private double shares;
    private double trade_price;
    



    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getShares() {
        return shares;
    }

    public double getTicker_price() {
        return ticker_price;
    }

    public void setTicker_price(double ticker_price) {
        this.ticker_price = ticker_price;
    }

    public void setShares(double shares) {
        this.shares = shares;
    }

    public double getTrade_price() {
        return trade_price;
    }

    public void setTrade_price(double trade_price) {
        this.trade_price = trade_price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLast_dividen() {
        return last_dividen;
    }

    public void setLast_dividen(double last_dividen) {
        this.last_dividen = last_dividen;
    }

    public double getFixed_dividen() {
        return fixed_dividen;
    }

    public void setFixed_dividen(double fixed_dividen) {
        this.fixed_dividen = fixed_dividen;
    }

    public double getPar_value() {
        return par_value;
    }

    public void setPar_value(double par_value) {
        this.par_value = par_value;
    }

    /*Methods*/
    public double dividenYield() {
        double result = 0;
        if (getType().contains("common") && getTicker_price() != 0) {
            result = (getLast_dividen() / getTicker_price());
        } else if (getType().contains("preferred") && getTicker_price() != 0) {
            result = (getFixed_dividen() / getTicker_price());
        }

        return result;
    }

    public double PE_Ratio() {
        double dividend = dividenYield();
        double result = 0;

        if (dividend != 0) {
            result = getTicker_price() / dividend;
        }

        return result;
    }



    public double stockPrice15(String stock) {

        String s = "";
        double result = 0;
        double sum1 = 0;
        double sum2 = 0;

        for (int i = 0; i < listStock.size(); i++) {

            s = listStock.get(i).getName();
            if (stock.toLowerCase().contains(s.toLowerCase())) { //If it is the same stock
//
                LocalDateTime d = LocalDateTime.now();
                long minutes = Duration.between(listStock.get(i).getTimestamp(), d).toMinutes();

                if (minutes <= 15) {
                    double price = listStock.get(i).getTrade_price();
                    double share = listStock.get(i).getShares();

                    sum1 += price * share;
                    sum2 += share;
                }
            }
        }

        if (sum2 > 0) {
            result = sum1 / sum2;
        }
        return result;
    }
    
    
    public double GBCEAllShare(){
        
        double result = 0;
        double mult = 1;
        for (int i = 0; i < listStock.size(); i++) {
            mult *= listStock.get(i).getTrade_price();
        }
        if (listStock.size()>= 1){
            result =  Math.pow(mult, 1.0/listStock.size()); ;
        }
        return result;
    }
}
