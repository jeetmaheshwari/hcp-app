package com.hcp.dao;

import com.google.gson.Gson;
import com.hcp.datasource.DatabaseManager;
import com.hcp.entity.CustomerItem;
import com.hcp.entity.Payment;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO
{
  private List<CustomerItem> customerItems = new ArrayList();
  private Statement stmt;
  private static final String SELECT = "select * from CustomerItem";
  private static final String UPDATE = "update CustomerItem set AUGBL = ";
  
  public CustomerDAO()
  {
    DatabaseManager dbManager = DatabaseManager.getInstance();
    this.stmt = DatabaseManager.getStatement();
  }
  
  public String getCustomerItems()
    throws Exception
  {
    ResultSet rs = this.stmt.executeQuery("select * from CustomerItem");
    this.customerItems = buildItems(rs);
    
    Gson gson = new Gson();
    String jsonString = gson.toJson(this.customerItems);
    return jsonString;
  }
  
  public CustomerItem getCustomerItem(String custNum, String docNumber)
    throws Exception
  {
    docNumber = "'" + docNumber + "'";
    custNum = "'" + custNum + "'";
    String query = "select * from CustomerItem where KUNNR = " + custNum + " and BELNR = " + docNumber;
    ResultSet rs = this.stmt.executeQuery(query);
    this.customerItems.clear();
    this.customerItems = buildItems(rs);
    return (CustomerItem)this.customerItems.get(0);
  }
  
  public int updateCustomerItem(String custNum, String docNumber)
    throws Exception
  {
    int updated = 0;
    List<Payment> payments = new PaymentDAO().getPayment(custNum, docNumber);
    docNumber = "'" + docNumber + "'";
    custNum = "'" + custNum + "'";
    for (Payment pmt : payments)
    {
      String laufi = pmt.getLaufi();
      laufi = "'" + laufi + "'";
      String query = "update CustomerItem set AUGBL = " + laufi + " where KUNNR = " + custNum + " and BELNR = " + docNumber;
      updated = this.stmt.executeUpdate(query);
    }
    return updated;
  }
  
  public int updateCustomerItem(String custNum, String docNumber, List<Payment> updatePayments)
    throws Exception
  {
    int updated = 0;
    docNumber = "'" + docNumber + "'";
    custNum = "'" + custNum + "'";
    for (Payment pmt : updatePayments)
    {
      String laufi = pmt.getLaufi();
      laufi = "'" + laufi + "'";
      String query = "update CustomerItem set AUGBL = " + laufi + " where KUNNR = " + custNum + " and BELNR = " + docNumber;
      updated = this.stmt.executeUpdate(query);
    }
    return updated;
  }
  
  private List<CustomerItem> buildItems(ResultSet rs)
    throws Exception
  {
    while ((rs != null) && (rs.next()))
    {
      String mandt = String.valueOf(rs.getString("MANDT"));
      String bukrs = rs.getString("BUKRS");
      String kunnr = rs.getString("KUNNR");
      String umsks = rs.getString("UMSKS");
      String umskz = rs.getString("UMSKZ");
      String augdt = rs.getString("AUGDT");
      String augbl = rs.getString("AUGBL");
      String zuonr = rs.getString("ZUONR");
      String gjahr = rs.getString("GJAHR");
      String belnr = rs.getString("BELNR");
      String buzei = rs.getString("BUZEI");
      String budat = rs.getString("BUDAT");
      String bldat = rs.getString("BLDAT");
      String cpudt = rs.getString("CPUDT");
      String dmbtr = rs.getString("DMBTR");
      String wrbtr = rs.getString("WRBTR");
      String waers = rs.getString("WAERS");
      String xblnr = rs.getString("XBLNR");
      String blart = rs.getString("BLART");
      String monat = rs.getString("MONAT");
      String bschl = rs.getString("BSCHL");
      String zumsk = rs.getString("ZUMSK");
      String shkzg = rs.getString("SHKZG");
      String gsber = rs.getString("GSBER");
      
      CustomerItem cItem = new CustomerItem();
      cItem.setMandt(mandt);
      cItem.setBukrs(bukrs);
      cItem.setKunnr(kunnr);
      cItem.setUmsks(umsks);
      cItem.setUmskz(umskz);
      cItem.setAugdt(augdt);
      cItem.setAugbl(augbl);
      cItem.setZuonr(zuonr);
      cItem.setGjahr(gjahr);
      cItem.setBelnr(belnr);
      cItem.setBuzei(buzei);
      cItem.setBudat(budat);
      cItem.setBldat(bldat);
      cItem.setCpudt(cpudt);
      cItem.setDmbtr(dmbtr);
      cItem.setWrbtr(wrbtr);
      cItem.setWaers(waers);
      cItem.setXblnr(xblnr);
      cItem.setBlart(blart);
      cItem.setMonat(monat);
      cItem.setBschl(bschl);
      cItem.setZumsk(zumsk);
      cItem.setShkzg(shkzg);
      cItem.setGsber(gsber);
      
      this.customerItems.add(cItem);
    }
    return this.customerItems;
  }
}
