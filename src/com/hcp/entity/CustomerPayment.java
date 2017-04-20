package com.hcp.entity;

public class CustomerPayment
{
  private String custNumber;
  private String custName;
  private String invNumber;
  private String invRef;
  private String recvdAmt;
  private String paymentRef;
  private String totalAmt;
  private String bankRef;
  private String match;
  
  public String getCustNumber()
  {
    return this.custNumber;
  }
  
  public void setCustNumber(String custNumber)
  {
    this.custNumber = custNumber;
  }
  
  public String getCustName()
  {
    return this.custName;
  }
  
  public void setCustName(String custName)
  {
    this.custName = custName;
  }
  
  public String getInvNumber()
  {
    return this.invNumber;
  }
  
  public void setInvNumber(String invNumber)
  {
    this.invNumber = invNumber;
  }
  
  public String getInvRef()
  {
    return this.invRef;
  }
  
  public void setInvRef(String invRef)
  {
    this.invRef = invRef;
  }
  
  public String getRecvdAmt()
  {
    return this.recvdAmt;
  }
  
  public void setRecvdAmt(String recvdAmt)
  {
    this.recvdAmt = recvdAmt;
  }
  
  public String getPaymentRef()
  {
    return this.paymentRef;
  }
  
  public void setPaymentRef(String paymentRef)
  {
    this.paymentRef = paymentRef;
  }
  
  public String getTotalAmt()
  {
    return this.totalAmt;
  }
  
  public void setTotalAmt(String totalAmt)
  {
    this.totalAmt = totalAmt;
  }
  
  public String getBankRef()
  {
    return this.bankRef;
  }
  
  public void setBankRef(String bankRef)
  {
    this.bankRef = bankRef;
  }
  
  public String getMatch()
  {
    return this.match;
  }
  
  public void setMatch(String match)
  {
    this.match = match;
  }
}
