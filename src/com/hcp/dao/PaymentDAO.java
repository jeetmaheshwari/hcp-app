package com.hcp.dao;

import com.google.gson.Gson;
import com.hcp.datasource.DatabaseManager;
import com.hcp.entity.CustomerPayment;
import com.hcp.entity.Payment;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO
{
  private Statement stmt;
  private List<Payment> payments = new ArrayList();
  private List<CustomerPayment> custPayments = new ArrayList();
  private static final String BASE_QUERY = "select * from Payment";
  private static final String BASE_CUST_QUERY = "select top 10 * from payments";
  
  public PaymentDAO()
  {
    DatabaseManager dbManager = DatabaseManager.getInstance();
    this.stmt = DatabaseManager.getStatement();
  }
  
  private void buildItems(ResultSet rs, String tableName)
    throws Exception
  {
    while ((rs != null) && (rs.next())) {
      if (tableName.equalsIgnoreCase("Payment"))
      {
        Payment payment = new Payment();
        
        String mandt = rs.getString("MANDT");
        String laufd = rs.getString("LAUFD");
        String laufi = rs.getString("LAUFI");
        String xvorl = rs.getString("XVORL");
        String zbukr = rs.getString("ZBUKR");
        String lifnr = rs.getString("LIFNR");
        String kunnr = rs.getString("KUNNR");
        String empfg = rs.getString("EMPFG");
        String vblnr = rs.getString("VBLNR");
        String bukrs = rs.getString("BUKRS");
        String belnr = rs.getString("BELNR");
        String gjahr = rs.getString("GJAHR");
        String buzei = rs.getString("BUZEI");
        String filkd = rs.getString("FILKD");
        String zlsch = rs.getString("ZLSCH");
        String waers = rs.getString("WAERS");
        String hbkid = rs.getString("HBKID");
        String bvtyp = rs.getString("BVTYP");
        String poken = rs.getString("POKEN");
        String xblnr = rs.getString("XBLNR");
        String blart = rs.getString("BLART");
        String budat = rs.getString("BUDAT");
        String bldat = rs.getString("BLDAT");
        String koart = rs.getString("KOART");
        String bschl = rs.getString("BSCHL");
        String hkont = rs.getString("HKONT");
        String saknr = rs.getString("SAKNR");
        String umskz = rs.getString("UMSKZ");
        String umsks = rs.getString("UMSKS");
        String zumsk = rs.getString("ZUMSK");
        String shkzg = rs.getString("SHKZG");
        String dmbtr = rs.getString("DMBTR");
        String wrbtr = rs.getString("WRBTR");
        
        payment.setMandt(mandt);
        payment.setVblnr(vblnr);
        payment.setFilkd(filkd);
        payment.setZlsch(zlsch);
        payment.setBvtyp(bvtyp);
        payment.setPoken(poken);
        payment.setKoart(koart);
        payment.setSaknr(saknr);
        payment.setHkont(hkont);
        payment.setHbkid(hbkid);
        payment.setBukrs(bukrs);
        payment.setKunnr(kunnr);
        payment.setUmsks(umsks);
        payment.setUmskz(umskz);
        payment.setLaufd(laufd);
        payment.setLaufi(laufi);
        payment.setXvorl(xvorl);
        payment.setGjahr(gjahr);
        payment.setBelnr(belnr);
        payment.setBuzei(buzei);
        payment.setBudat(budat);
        payment.setBldat(bldat);
        payment.setZbukr(zbukr);
        payment.setDmbtr(dmbtr);
        payment.setWrbtr(wrbtr);
        payment.setWaers(waers);
        payment.setXblnr(xblnr);
        payment.setBlart(blart);
        payment.setLifnr(lifnr);
        payment.setBschl(bschl);
        payment.setZumsk(zumsk);
        payment.setShkzg(shkzg);
        payment.setEmpfg(empfg);
        this.payments.add(payment);
      }
      else
      {
        CustomerPayment custPayment = new CustomerPayment();
        
        custPayment.setCustName(rs.getString(1));
        custPayment.setCustNumber(rs.getString(2));
        custPayment.setInvNumber(rs.getString(3));
        custPayment.setInvRef(rs.getString(4));
        custPayment.setRecvdAmt(rs.getString(5));
        custPayment.setPaymentRef(rs.getString(6));
        custPayment.setTotalAmt(rs.getString(7));
        custPayment.setBankRef(rs.getString(8));
        custPayment.setMatch(rs.getString(9));
        this.custPayments.add(custPayment);
      }
    }
  }
  
  public String getPayments()
    throws Exception
  {
    ResultSet rs = this.stmt.executeQuery("select * from Payment");
    String tableName = "Payment";
    buildItems(rs, tableName);
    
    Gson gson = new Gson();
    String jsonString = gson.toJson(this.payments);
    return jsonString;
  }
  
  public String getCustomerPayments(String custNum)
    throws Exception
  {
    String query = "select top 10 * from payments where CustomerNumber = " + Integer.parseInt(custNum);
    System.out.println("Query exex: " + query);
    ResultSet rs = this.stmt.executeQuery(query);
    String tableName = "Payments";
    buildItems(rs, tableName);
    
    Gson gson = new Gson();
    String jsonString = gson.toJson(this.custPayments);
    return jsonString;
  }
  
  public List<Payment> getPayment(String custNum, String docNumber)
    throws Exception
  {
    docNumber = "'" + docNumber + "'";
    custNum = "'" + custNum + "'";
    String query = "select * from Payment  where KUNNR = " + custNum + " and BELNR = " + docNumber;
    ResultSet rs = this.stmt.executeQuery(query);
    this.payments.clear();
    String tableName = "Payment";
    buildItems(rs, tableName);
    return this.payments;
  }
}
