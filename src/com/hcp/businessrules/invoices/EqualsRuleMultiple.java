package com.hcp.businessrules.invoices;

import com.hcp.businessrules.BasicInvoiceRule;
import com.hcp.dao.CustomerDAO;
import com.hcp.dao.PaymentDAO;
import com.hcp.entity.Payment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response;

public class EqualsRuleMultiple
  extends BasicInvoiceRule
{
  private boolean status;
  private String custNum;
  private List<Payment> updatePayments;
  private CustomerDAO customerDAO;
  private float thresholdAmt = 100.0F;
  
  public EqualsRuleMultiple(String custNum, String amount)
  {
    this.custNum = custNum;
    this.thresholdAmt = Float.valueOf(amount).floatValue();
  }
  
  public void evaluate()
    throws Exception
  {
    this.customerDAO = new CustomerDAO();
    List<String> docNumbers = getDocNumbers();
    for (String docNumber : docNumbers)
    {
      List<Payment> payments = new PaymentDAO().getPayment(this.custNum, docNumber);
      Arrays.sort(payments.toArray());
      float result = 0.0F;
      if (payments != null)
      {
        this.updatePayments = new ArrayList();
        for (Payment payment : payments)
        {
          String pmtDmbtr = payment.getDmbtr();
          float value = Float.valueOf(pmtDmbtr).floatValue();
          if (result + value <= this.thresholdAmt)
          {
            result += value;
            this.updatePayments.add(payment);
            this.status = true;
          }
          else
          {
            throw new Exception(Response.Status.NOT_ACCEPTABLE.getReasonPhrase());
          }
        }
      }
    }
  }
  
  public void execute()
    throws Exception
  {
    if (this.status) {
      for (String docNumber : getDocNumbers())
      {
        int updated = this.customerDAO.updateCustomerItem(this.custNum, docNumber, this.updatePayments);
        setUpdated(updated);
      }
    }
  }
}
