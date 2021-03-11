import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

class stock{

    String symbol;
    String price;
    String volume;
    String market_cap;
    String full;
    static String[] list = new String[20];

    public stock(String symbol)throws IOException{


        this.symbol = symbol;
        this.price=getPrice(symbol);
        this.volume=getVolume(symbol);
        this.market_cap=getm_cap(symbol);
        this.full=stock_info(symbol,price,volume, market_cap);
        
    }

    public static String findInfo(String symbol,String Search, String keyWord)throws IOException{

        String info = "";
        URL url = new URL("https://markets.businessinsider.com/stocks/" + symbol +"-stock");
        URLConnection urlConn = url.openConnection();
        InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
        BufferedReader buff = new BufferedReader(inStream);
        String line = buff.readLine();

        while( line != null){
            if(line.contains(Search)){
                for(int i=0; i < list.length; i++){
                    list[i] = line;
                    line=buff.readLine();
                }
                break;
            }
            line = buff.readLine();
        }
        
        for(int i=0; i < list.length; i++){
            
            if(list[i].contains(keyWord)){

                info = list[i-1];
            }
           
        }

        info = info.replaceAll("\\s","");

        return info;

    }

    public static String getPrice(String symbol) throws IOException{

        String s = findInfo(symbol,"var snaps","highDisplay");
        String temp = s.substring(6,s.length()-1);
        
        return temp;
        
    }
    
    public static String getVolume(String symbol) throws IOException{

        return findInfo(symbol,"header-underline--no","Number of Shares");
       
    }

    public static String getm_cap(String symbol) throws IOException{

        return findInfo(symbol,"header-underline--no","Market Cap");

    }

    public static String stock_info(String symbol, String price, String volume,String market_cap) {

        symbol = format_input(symbol);
        price  = format_input(price);
        volume = format_input(volume);
        if(market_cap.equals("")){
            market_cap = " N/A";
        }

        System.out.println("Ticker: " + symbol + "Price: $" + price + "Volume: " + volume + "Market Cap: " + market_cap);
        return "Ticker: " + symbol + "Price: $" + price + "Volume: " + volume + "Market Cap: " + market_cap;

    }

    public static String format_input(String s){
        
        String temp = s;

        if(temp.equals("")){
            temp = " N/A";
        }

        while(temp.length() < 10){
            temp += " ";
        }  

        return temp;   
    }
}