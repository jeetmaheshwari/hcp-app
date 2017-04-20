package com.hcp.entity;

import java.io.PrintStream;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Payment")
@NamedQuery(name="AllPayments", query="select p from Payment p")
public class Payment
  implements Serializable, Comparable<Payment>
{
  @Id
  private String mandt;
  private String laufd;
  private String laufi;
  private String xvorl;
  private String zbukr;
  private String lifnr;
  private String kunnr;
  private String empfg;
  private String vblnr;
  private String bukrs;
  private String belnr;
  private String gjahr;
  private String buzei;
  private String filkd;
  private String zlsch;
  private String waers;
  private String hbkid;
  private String bvtyp;
  private String poken;
  private String xblnr;
  private String blart;
  private String budat;
  private String bldat;
  private String koart;
  private String bschl;
  private String hkont;
  private String saknr;
  private String umskz;
  private String umsks;
  private String zumsk;
  private String shkzg;
  private String dmbtr;
  private String wrbtr;
  private static final long serialVersionUID = 1L;
  
  public String getMandt()
  {
    return this.mandt;
  }
  
  public void setMandt(String mandt)
  {
    this.mandt = mandt;
  }
  
  public String getLaufd()
  {
    return this.laufd;
  }
  
  public void setLaufd(String laufd)
  {
    this.laufd = laufd;
  }
  
  public String getLaufi()
  {
    return this.laufi;
  }
  
  public void setLaufi(String laufi)
  {
    this.laufi = laufi;
  }
  
  public String getXvorl()
  {
    return this.xvorl;
  }
  
  public void setXvorl(String xvorl)
  {
    this.xvorl = xvorl;
  }
  
  public String getZbukr()
  {
    return this.zbukr;
  }
  
  public void setZbukr(String zbukr)
  {
    this.zbukr = zbukr;
  }
  
  public String getLifnr()
  {
    return this.lifnr;
  }
  
  public void setLifnr(String lifnr)
  {
    this.lifnr = lifnr;
  }
  
  public String getKunnr()
  {
    return this.kunnr;
  }
  
  public void setKunnr(String kunnr)
  {
    this.kunnr = kunnr;
  }
  
  public String getEmpfg()
  {
    return this.empfg;
  }
  
  public void setEmpfg(String empfg)
  {
    this.empfg = empfg;
  }
  
  public String getVblnr()
  {
    return this.vblnr;
  }
  
  public void setVblnr(String vblnr)
  {
    this.vblnr = vblnr;
  }
  
  public String getBukrs()
  {
    return this.bukrs;
  }
  
  public void setBukrs(String bukrs)
  {
    this.bukrs = bukrs;
  }
  
  public String getBelnr()
  {
    return this.belnr;
  }
  
  public void setBelnr(String belnr)
  {
    this.belnr = belnr;
  }
  
  public String getGjahr()
  {
    return this.gjahr;
  }
  
  public void setGjahr(String gjahr)
  {
    this.gjahr = gjahr;
  }
  
  public String getBuzei()
  {
    return this.buzei;
  }
  
  public void setBuzei(String buzei)
  {
    this.buzei = buzei;
  }
  
  public String getFilkd()
  {
    return this.filkd;
  }
  
  public void setFilkd(String filkd)
  {
    this.filkd = filkd;
  }
  
  public String getZlsch()
  {
    return this.zlsch;
  }
  
  public void setZlsch(String zlsch)
  {
    this.zlsch = zlsch;
  }
  
  public String getWaers()
  {
    return this.waers;
  }
  
  public void setWaers(String waers)
  {
    this.waers = waers;
  }
  
  public String getHbkid()
  {
    return this.hbkid;
  }
  
  public void setHbkid(String hbkid)
  {
    this.hbkid = hbkid;
  }
  
  public String getBvtyp()
  {
    return this.bvtyp;
  }
  
  public void setBvtyp(String bvtyp)
  {
    this.bvtyp = bvtyp;
  }
  
  public String getPoken()
  {
    return this.poken;
  }
  
  public void setPoken(String poken)
  {
    this.poken = poken;
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
  
  public String getKoart()
  {
    return this.koart;
  }
  
  public void setKoart(String koart)
  {
    this.koart = koart;
  }
  
  public String getBschl()
  {
    return this.bschl;
  }
  
  public void setBschl(String bschl)
  {
    this.bschl = bschl;
  }
  
  public String getHkont()
  {
    return this.hkont;
  }
  
  public void setHkont(String hkont)
  {
    this.hkont = hkont;
  }
  
  public String getSaknr()
  {
    return this.saknr;
  }
  
  public void setSaknr(String saknr)
  {
    this.saknr = saknr;
  }
  
  public String getUmskz()
  {
    return this.umskz;
  }
  
  public void setUmskz(String umskz)
  {
    this.umskz = umskz;
  }
  
  public String getUmsks()
  {
    return this.umsks;
  }
  
  public void setUmsks(String umsks)
  {
    this.umsks = umsks;
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
  
  public int compareTo(Payment o)
  {
    String dmbtr = o.getDmbtr();
    float dmbtrVal = Float.valueOf(dmbtr).floatValue();
    float thisDmbtr = Float.valueOf(this.dmbtr).floatValue();
    System.out.println("In compare to : " + thisDmbtr + " : " + dmbtrVal);
    float compare = thisDmbtr - dmbtrVal;
    System.out.println("Compared val : " + compare + " :  result " + Double.valueOf(compare).intValue());
    return Double.valueOf(compare).intValue();
  }
}
