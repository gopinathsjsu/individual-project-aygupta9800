package com.sample.interfaces;
import javax.servlet.http.Part;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public interface IOrderInterface {
    public String isValidOrderRequest(String str[]) throws IOException;
    public double calculateTotalAmountPaid(String inputContent[]);
    public int getCapValue(String categoryName);
}
