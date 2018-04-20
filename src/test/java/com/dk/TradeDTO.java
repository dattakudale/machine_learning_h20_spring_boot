package com.dk;

import org.springframework.web.bind.annotation.RequestParam;

public class TradeDTO {

	Double open;
	Double high;
	Double low;
	Double lower_band;
	Double middle_band;
	Double upper_band;
	Double macd;
	Double macd_signal;
	Double rsi;
	Double cci;
	Double dpercent;
	Double kpercent;
	Double sma50;
	Double sma100;
	Double momentum;
	Double close;
	
	String signalType;

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getLower_band() {
		return lower_band;
	}

	public void setLower_band(Double lower_band) {
		this.lower_band = lower_band;
	}

	public Double getMiddle_band() {
		return middle_band;
	}

	public void setMiddle_band(Double middle_band) {
		this.middle_band = middle_band;
	}

	public Double getUpper_band() {
		return upper_band;
	}

	public void setUpper_band(Double upper_band) {
		this.upper_band = upper_band;
	}

	public Double getMacd() {
		return macd;
	}

	public void setMacd(Double macd) {
		this.macd = macd;
	}

	public Double getMacd_signal() {
		return macd_signal;
	}

	public void setMacd_signal(Double macd_signal) {
		this.macd_signal = macd_signal;
	}

	public Double getRsi() {
		return rsi;
	}

	public void setRsi(Double rsi) {
		this.rsi = rsi;
	}

	public Double getCci() {
		return cci;
	}

	public void setCci(Double cci) {
		this.cci = cci;
	}

	public Double getDpercent() {
		return dpercent;
	}

	public void setDpercent(Double dpercent) {
		this.dpercent = dpercent;
	}

	public Double getKpercent() {
		return kpercent;
	}

	public void setKpercent(Double kpercent) {
		this.kpercent = kpercent;
	}

	public Double getSma50() {
		return sma50;
	}

	public void setSma50(Double sma50) {
		this.sma50 = sma50;
	}

	public Double getSma100() {
		return sma100;
	}

	public void setSma100(Double sma100) {
		this.sma100 = sma100;
	}

	public Double getMomentum() {
		return momentum;
	}

	public void setMomentum(Double momentum) {
		this.momentum = momentum;
	}

	public Double getClose() {
		return close;
	}

	public void setClose(Double close) {
		this.close = close;
	}
	
	

	public String getSignalType() {
		return signalType;
	}

	public void setSignalType(String signalType) {
		this.signalType = signalType;
	}

	public String toURLString() {
		return "open=" + open + "&high=" + high + "&low=" + low + "&lower_band=" + lower_band + "&middle_band="
				+ middle_band + "&upper_band=" + upper_band + "&macd=" + macd + "&macd_signal=" + macd_signal + "&rsi="
				+ rsi + "&cci=" + cci + "&dpercent=" + dpercent + "&kpercent=" + kpercent + "&sma50=" + sma50
				+ "&sma100=" + sma100 + "&momentum=" + momentum + "&close=" + close;
	}

}
