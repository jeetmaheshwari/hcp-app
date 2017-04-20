package com.hcp.businessrules.invoices;

import com.hcp.businessrules.BasicInvoiceRule;
import com.hcp.dao.CustomerDAO;
import com.hcp.dao.PaymentDAO;
import com.hcp.entity.CustomerItem;
import com.hcp.entity.Payment;
import java.util.List;

public class EqualsRule
  extends BasicInvoiceRule
{
  private boolean status;
  private String custNum;
  private String docNumber;
  private List<CustomerItem> customerItems;
  private List<Payment> payments;
  private CustomerDAO customerDAO;
  private Payment payment;
  
  public EqualsRule(String custNum)
  {
    this.custNum = custNum;
  }
  
  public void evaluate()
  {
    try
    {
      this.customerDAO = new CustomerDAO();
      List<String> docNumbers = getDocNumbers();
      this.docNumber = ((String)docNumbers.get(0));
      CustomerItem customerItem = this.customerDAO.getCustomerItem(this.custNum, this.docNumber);
      
      this.payments = new PaymentDAO().getPayment(this.custNum, this.docNumber);
      if (this.payments.get(0) != null) {
        this.payment = ((Payment)this.payments.get(0));
      }
      String custKunnr = customerItem.getKunnr();
      String pmtKunnr = this.payment.getKunnr();
      String custBelnr = customerItem.getBelnr();
      String pmtBelnr = this.payment.getBelnr();
      
      String custDmbtr = customerItem.getDmbtr();
      String pmtDmbtr = this.payment.getDmbtr();
      if ((customerItem != null) && (custKunnr != null) && (pmtKunnr != null) && (custKunnr.equals(pmtKunnr)) && 
        (custBelnr != null) && (pmtBelnr != null) && (custBelnr.equals(pmtBelnr)) && 
        (custDmbtr != null) && (pmtDmbtr != null) && (custDmbtr.equals(pmtDmbtr))) {
        this.status = true;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void execute()
    throws Exception
  {
    if (this.status)
    {
      int updated = this.customerDAO.updateCustomerItem(this.custNum, this.docNumber);
      setUpdated(updated);
    }
  }
}
