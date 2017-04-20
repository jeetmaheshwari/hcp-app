package com.hcp.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="CustomerItem")
@NamedQuery(name="AllItems", query="select c from CustomerItem c")
public class CustomerItem
  implements Serializable
{
  @Id
  private String mandt;
  private String bukrs;
  private String kunnr;
  private String umsks;
  private String umskz;
  private String augdt;
  private String augbl;
  private String zuonr;
  private String gjahr;
  private String belnr;
  private String buzei;
  private String budat;
  private String bldat;
  private String cpudt;
  private String dmbtr;
  private String wrbtr;
  private String waers;
  private String xblnr;
  private String blart;
  private String monat;
  private String bschl;
  private String zumsk;
  private String shkzg;
  private String gsber;
  private static final long serialVersionUID = 1L;
  
  public String getMandt()
  {
    return this.mandt;
  }
  
  public void setMandt(String mandt)
  {
    this.mandt = mandt;
  }
  
  public String getBukrs()
  {
    return this.bukrs;
  }
  
  public void setBukrs(String bukrs)
  {
    this.bukrs = bukrs;
  }
  
  public String getKunnr()
  {
    return this.kunnr;
  }
  
  public void setKunnr(String kunnr)
  {
    this.kunnr = kunnr;
  }
  
  public String getUmsks()
  {
    return this.umsks;
  }
  
  public void setUmsks(String umsks)
  {
    this.umsks = umsks;
  }
  
  public String getUmskz()
  {
    return this.umskz;
  }
  
  public void setUmskz(String umskz)
  {
    this.umskz = umskz;
  }
  
  public String getAugdt()
  {
    return this.augdt;
  }
  
  public void setAugdt(String augdt)
  {
    this.augdt = augdt;
  }
  
  public String getAugbl()
  {
    return this.augbl;
  }
  
  public void setAugbl(String augbl)
  {
    this.augbl = augbl;
  }
  
  public String getZuonr()
  {
    return this.zuonr;
  }
  
  public void setZuonr(String zuonr)
  {
    this.zuonr = zuonr;
  }
  
  public String getGjahr()
  {
    return this.gjahr;
  }
  
  public void setGjahr(String gjahr)
  {
    this.gjahr = gjahr;
  }
  
  public String getBelnr()
  {
    return this.belnr;
  }
  
  public void setBelnr(String belnr)
  {
    this.belnr = belnr;
  }
  
  public String getBuzei()
  {
    return this.buzei;
  }
  
  public void setBuzei(String buzei)
  {
    this.buzei = buzei;
  }
  
  public String getBudat()
  {
    return this.budat;
  }
  
  public void setBudat(String budat)
  {
    this.budat = budat;
  }
  
  public String getBldat()
  {
    return this.bldat;
  }
  
  public void setBldat(String bldat)
  {
    this.bldat = bldat;
  }
  
  public String getCpudt()
  {
    return this.cpudt;
  }
  
  public void setCpudt(String cpudt)
  {
    this.cpudt = cpudt;
  }
  
  public String getDmbtr()
  {
    return this.dmbtr;
  }
  
  public void setDmbtr(String dmbtr)
  {
    this.dmbtr = dmbtr;
  }
  
  public String getWrbtr()
  {
    return this.wrbtr;
  }
  
  public void setWrbtr(String wrbtr)
  {
    this.wrbtr = wrbtr;
  }
  
  public String getWaers()
  {
    return this.waers;
  }
  
  public void setWaers(String waers)
  {
    this.waers = waers;
  }
  
  public String getXblnr()
  {
    return this.xblnr;
  }
  
  public void setXblnr(String xblnr)
  {
    this.xblnr = xblnr;
  }
  
  public String getBlart()
  {
    return this.blart;
  }
  
  public void setBlart(String blart)
  {
    this.blart = blart;
  }
  
  public String getMonat()
  {
    return this.monat;
  }
  
  public void setMonat(String monat)
  {
    this.monat = monat;
  }
  
  public String getBschl()
  {
    return this.bschl;
  }
  
  public void setBschl(String bschl)
  {
    this.bschl = bschl;
  }
  
  public String getZumsk()
  {
    return this.zumsk;
  }
  
  public void setZumsk(String zumsk)
  {
    this.zumsk = zumsk;
  }
  
  public String getShkzg()
  {
    return this.shkzg;
  }
  
  public void setShkzg(String shkzg)
  {
    this.shkzg = shkzg;
  }
  
  public String getGsber()
  {
    return this.gsber;
  }
  
  public void setGsber(String gsber)
  {
    this.gsber = gsber;
  }
}
