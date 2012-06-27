package org.xpup.hafmis.syscollection.dto;
import java.io.Serializable;
import java.math.BigDecimal;
/**
 * @author 王玲
 * 2007-6-26
 * 用于存放计算年终利息的各参数
 */
public class ClearingInterestDTO implements Serializable{

  private static final long serialVersionUID = 0L;
  
  private BigDecimal curIntegral = new BigDecimal(0.00);//本年积数
  private BigDecimal preIntegral = new BigDecimal(0.00);//往年积数
  private BigDecimal curRate = new BigDecimal(0.00);//本年利率
  private BigDecimal preRate = new BigDecimal(0.00);//往年利率
  
  private BigDecimal  curIntegealSubA = new BigDecimal(0.00);//分段本年积数a
  private BigDecimal  preIntegealSubA = new BigDecimal(0.00);//分段往年积数a
  private BigDecimal  curRateA = new BigDecimal(0.00);//本年利率a
  private BigDecimal  preRateA = new BigDecimal(0.00);//往年利率a
  
  private BigDecimal  curIntegealSubB = new BigDecimal(0.00);//分段本年积数b
  private BigDecimal  preIntegealSubB = new BigDecimal(0.00);//分段往年积数b
  private BigDecimal  curRateB = new BigDecimal(0.00);//本年利率b
  private BigDecimal  preRateB = new BigDecimal(0.00);//往年利率b
  
  private BigDecimal  curIntegealSubC = new BigDecimal(0.00);//分段本年积数c
  private BigDecimal  preIntegealSubC = new BigDecimal(0.00);//分段往年积数c
  private BigDecimal  curRateC = new BigDecimal(0.00);//本年利率c
  private BigDecimal  preRateC = new BigDecimal(0.00);//往年利率c
  
  public BigDecimal getCurIntegealSubA() {
    return curIntegealSubA;
  }
  public void setCurIntegealSubA(BigDecimal curIntegealSubA) {
    this.curIntegealSubA = curIntegealSubA;
  }
  public BigDecimal getCurIntegealSubB() {
    return curIntegealSubB;
  }
  public void setCurIntegealSubB(BigDecimal curIntegealSubB) {
    this.curIntegealSubB = curIntegealSubB;
  }
  public BigDecimal getCurIntegealSubC() {
    return curIntegealSubC;
  }
  public void setCurIntegealSubC(BigDecimal curIntegealSubC) {
    this.curIntegealSubC = curIntegealSubC;
  }
  public BigDecimal getCurIntegral() {
    return curIntegral;
  }
  public void setCurIntegral(BigDecimal curIntegral) {
    this.curIntegral = curIntegral;
  }
  public BigDecimal getCurRate() {
    return curRate;
  }
  public void setCurRate(BigDecimal curRate) {
    this.curRate = curRate;
  }
  public BigDecimal getCurRateA() {
    return curRateA;
  }
  public void setCurRateA(BigDecimal curRateA) {
    this.curRateA = curRateA;
  }
  public BigDecimal getCurRateB() {
    return curRateB;
  }
  public void setCurRateB(BigDecimal curRateB) {
    this.curRateB = curRateB;
  }
  public BigDecimal getCurRateC() {
    return curRateC;
  }
  public void setCurRateC(BigDecimal curRateC) {
    this.curRateC = curRateC;
  }
  public BigDecimal getPreIntegealSubA() {
    return preIntegealSubA;
  }
  public void setPreIntegealSubA(BigDecimal preIntegealSubA) {
    this.preIntegealSubA = preIntegealSubA;
  }
  public BigDecimal getPreIntegealSubB() {
    return preIntegealSubB;
  }
  public void setPreIntegealSubB(BigDecimal preIntegealSubB) {
    this.preIntegealSubB = preIntegealSubB;
  }
  public BigDecimal getPreIntegealSubC() {
    return preIntegealSubC;
  }
  public void setPreIntegealSubC(BigDecimal preIntegealSubC) {
    this.preIntegealSubC = preIntegealSubC;
  }
  public BigDecimal getPreIntegral() {
    return preIntegral;
  }
  public void setPreIntegral(BigDecimal preIntegral) {
    this.preIntegral = preIntegral;
  }

  public BigDecimal getPreRate() {
    return preRate;
  }
  public void setPreRate(BigDecimal preRate) {
    this.preRate = preRate;
  }
  public BigDecimal getPreRateA() {
    return preRateA;
  }
  public void setPreRateA(BigDecimal preRateA) {
    this.preRateA = preRateA;
  }
  public BigDecimal getPreRateB() {
    return preRateB;
  }
  public void setPreRateB(BigDecimal preRateB) {
    this.preRateB = preRateB;
  }
  public BigDecimal getPreRateC() {
    return preRateC;
  }
  public void setPreRateC(BigDecimal preRateC) {
    this.preRateC = preRateC;
  }
}
