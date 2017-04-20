package com.hcp.businessrules;

import java.util.List;

public abstract class BasicInvoiceRule
{
  private List<String> docNumbers;
  private RuleDescription ruleDesc;
  private int updated;
  
  public void setUpdated(int updated)
  {
    this.updated = updated;
  }
  
  public void setDocNumbers(List<String> docNumbers)
  {
    this.docNumbers = docNumbers;
  }
  
  public List<String> getDocNumbers()
  {
    return this.docNumbers;
  }
  
  public void setRuleDesc(RuleDescription ruleDesc)
  {
    this.ruleDesc = ruleDesc;
  }
  
  public int getUpdated()
  {
    return this.updated;
  }
  
  public abstract void evaluate()
    throws Exception;
  
  public abstract void execute()
    throws Exception;
}
