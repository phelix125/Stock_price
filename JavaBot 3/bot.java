import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class bot {

    static String [] list_Stocks = new String[]{"AGTC","CTXS","CTXR","LGHL","TRCH","SFT","AAPL",
                                                "PULM","ACRX","ASRT","OBSV","SHIP","HEPA",
                                                "TSLA","AMC","GME","F","GE","DIS","MSFT","GE","AMZN",
                                                "NOK","APHA","AAL","BA","PLUG","PFE","CCL","GPRO",
                                                "DAL","SNAP","BABA","NFLX","FB","T","GM","KO","UBER"};
    public static void main(String[] args) throws IOException{

        stock[] arr = new stock[list_Stocks.length];

        try(FileWriter myWriter = new FileWriter("records1.txt")){

            for(int i=0; i< list_Stocks.length; i++){

                stock p = new stock(list_Stocks[i]);
                arr[i] = p;

                String str = p.full;
                myWriter.write(str);
                myWriter.write(System.getProperty( "line.separator" ));
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        
    }

  
}

    
