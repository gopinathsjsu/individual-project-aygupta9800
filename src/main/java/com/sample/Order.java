package com.sample;
import com.sample.model.Cards;
import com.sample.model.Item;
import com.sample.model.Inventory;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.HashMap;
import com.sample.interfaces.IOrderInterface;
import java.util.*;


@WebServlet(
        name = "OrderServlet",
        urlPatterns = "/order"
)
@MultipartConfig
public class Order implements IOrderInterface {
    private static final int BUFFER_SIZE = 2048;
    Inventory inventoryObj = new Inventory();
     public void processOrder(String paths) {
        StringBuilder value = new StringBuilder();
        try
        {
            // parsing a CSV file into BufferedReader class constructor
            // String paths = "/Users/ayushgupta/Documents/Workspace/202_Gopi/individual-project/Input1-Sheet1.csv";
            BufferedReader br = new BufferedReader(new FileReader(paths));
            char[] buffer = new char[BUFFER_SIZE];
                for (int length = 0; (length = br.read(buffer)) > 0; ) {
                    value.append(buffer, 0, length);
                }
//                System.out.println("CONTENT:" +value.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        String[] inputContent = value.toString().split("\n");

        try {
            OutputStream outputStream = new FileOutputStream("output.txt");
            String outputTxt;
            String invalidRequestStr = isValidOrderRequest(inputContent);
            if (invalidRequestStr.length() == 0) {
                Cards cards = new Cards();
                for(int i = 1; i < inputContent.length; i++ ){
                    if(!cards.getCardList().contains(getCardNumber(inputContent[i]))){
//                        System.out.println((inputContent[i]).getClass().getName());
                        cards.getCardList().add(getCardNumber(inputContent[i]));
                        cards.setCardList(cards.getCardList());
                    }
                }
//                cards.getCardList().add(5023451276129087);
//                cards.setCardList(cards.getCardList());
//                cards.getCardList().add(1234567891014519);
//                cards.setCardList(cards.getCardList());
                List<Integer> cardsList = cards.getCardList();
                for(int i=0;i<cardsList.size();i++){
                    System.out.println("cardno." + cardsList.get(i));
                }
                double totalAmountPaid = calculateTotalAmountPaid(inputContent);
                outputTxt = "Total amount paid: \n "+ totalAmountPaid;
            } else {
                outputTxt = "Please correct quantities\n"+ invalidRequestStr;
            }
            System.out.println(outputTxt);
            outputStream.write(outputTxt.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String isValidOrderRequest(String[] inputContent) throws IOException {
        String invalidRequestString = "";
        Inventory inventory =(Inventory) inventoryObj.getClone();
        HashMap<String, Integer> categoryCountHashMap = new HashMap<>();
        for(int i = 1; i < inputContent.length; i++){
            String orderedItemName = getItemName(inputContent[i]);
            int orderedItemQuantity = getItemQuantity(inputContent[i]);
            String itemCategory = inventory.getCategory(orderedItemName);
            int val = categoryCountHashMap.getOrDefault(itemCategory, 0)  + orderedItemQuantity;
            if(val > getCapValue(itemCategory)){
                System.out.println("Cap Value =" + getCapValue(itemCategory));
                System.out.println("itemCategory =" + itemCategory);
                invalidRequestString = "Max limit for Luxury: 3, Essential: 5 \n and Misc: 6";
            }
            categoryCountHashMap.put(itemCategory, categoryCountHashMap.getOrDefault(itemCategory, 0) + orderedItemQuantity);
            System.out.println(categoryCountHashMap);
            for(Map.Entry<String, Item> set: inventory.itemMap.entrySet()){
                if(set.getKey().equalsIgnoreCase(orderedItemName)){
                    if(set.getValue().getAvailableQuantity() < orderedItemQuantity){
                        invalidRequestString += inputContent[i]+"\n";
                    }
                }

            }
        }
        return invalidRequestString;
    }

    @Override
    public double calculateTotalAmountPaid(String inputContent[]){
        double amountPaid = 0;
        Inventory inventory =(Inventory) inventoryObj.getClone();
        for(int i = 1; i < inputContent.length; i++ ){
            String orderedItemName = getItemName(inputContent[i]);
            int orderedItemQuantity = getItemQuantity(inputContent[i]);
            for(Map.Entry<String, Item> set: inventory.itemMap.entrySet()){
                if(set.getKey().equalsIgnoreCase(orderedItemName)){
                        amountPaid += set.getValue().getPrice() * orderedItemQuantity;
                }

            }
        }
        return amountPaid;
    }

    private int getItemQuantity(String orderStr) {
        return Integer.parseInt(orderStr.split(",")[1]);
    }

    private String getItemName(String orderStr) {
        return orderStr.split(",")[0];
    }

    private Integer getCardNumber(String orderStr) {
        return Integer.getInteger(orderStr.split(",")[2]);
    }

    @Override
    public int getCapValue(String categoryName) {
        Inventory item =(Inventory) inventoryObj.getClone();
        if(categoryName.equalsIgnoreCase("Essential")){
            return item.getEssentialCapValue();
        }else if(categoryName.equalsIgnoreCase("Misc")){
            return item.getMiscCapValue();
        }else if(categoryName.equalsIgnoreCase("Luxury")){
            return item.getLuxaryCapValue();
        }
        return 0;
    }
}
