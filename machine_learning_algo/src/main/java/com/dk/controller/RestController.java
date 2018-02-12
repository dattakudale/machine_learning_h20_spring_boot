package com.dk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.prediction.BinomialModelPrediction;
import hex.genmodel.easy.prediction.MultinomialModelPrediction;
import hex.genmodel.easy.prediction.RegressionModelPrediction;

@Controller
public class RestController {

	// http://localhost:8083/getSignal?cci=1&z_score=11&ema100=1&upper_band=200&macd=100&close=200

	@Autowired
	@Qualifier("GBM")
	private EasyPredictModelWrapper model;

	@Autowired
	@Qualifier("AutoMLStackedModel")
	private EasyPredictModelWrapper autMLModel;

	@RequestMapping(value = "/getSignal", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody double receivePriceFeedEnabled(@RequestParam(value = "cci") String cci,
			@RequestParam(value = "z_score") String z_score, @RequestParam(value = "ema100") String ema100,
			@RequestParam(value = "upper_band") String upper_band, @RequestParam(value = "macd") String macd,
			@RequestParam(value = "close") String close) throws Exception {

		RegressionModelPrediction p = null;
		RowData row1 = new RowData();
		row1.put("cci", cci);
		row1.put("z_score", z_score);
		row1.put("ema100", ema100);
		row1.put("upper_band", upper_band);
		row1.put("macd", macd);
		row1.put("close", close);

		p = (RegressionModelPrediction) model.predict(row1);

		System.out.println("Predicted value : " + p.value);

		return p.value;
	}

	/*
	 * 
	 * open high low lower_band middle_band upper_band macd macd_signal rsi cci
	 * dpercent kpercent sma50 sma100 momentum close signal_type
	 */

	// http://localhost:8085/getSignalBinomial??sma100=13&sma50=12&macd=6&cci=9&lower_band=3&rsi=8&middle_band=4&momentum=14&signal_type=16&high=1&low=2&macd_signal=7&upper_band=5&dpercent=10&kpercent=11&close=15&open=0

	@RequestMapping(value = "/getSignalBinomial", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getSignalBinomial(@RequestParam(value = "open") Double open,
			@RequestParam(value = "high") Double high, @RequestParam(value = "low") Double low,
			@RequestParam(value = "lower_band") Double lower_band,
			@RequestParam(value = "middle_band") Double middle_band,
			@RequestParam(value = "upper_band") Double upper_band, @RequestParam(value = "macd") Double macd,
			@RequestParam(value = "macd_signal") Double macd_signal, @RequestParam(value = "rsi") Double rsi,
			@RequestParam(value = "cci") Double cci, @RequestParam(value = "dpercent") Double dpercent,
			@RequestParam(value = "kpercent") Double kpercent, @RequestParam(value = "sma50") Double sma50,
			@RequestParam(value = "sma100") Double sma100, @RequestParam(value = "momentum") Double momentum,
			@RequestParam(value = "close") Double close) throws Exception {

		BinomialModelPrediction p = null;
		RowData row1 = new RowData();

		row1.put("open", open);
		row1.put("high", high);
		row1.put("low", low);
		row1.put("lower_band", lower_band);
		row1.put("middle_band", middle_band);

		row1.put("upper_band", upper_band);
		row1.put("macd", macd);
		row1.put("macd_signal", macd_signal);
		row1.put("rsi", rsi);
		row1.put("cci", cci);
		row1.put("dpercent", dpercent);
		row1.put("kpercent", kpercent);
		row1.put("sma50", sma50);
		row1.put("sma100", sma100);
		row1.put("momentum", momentum);
		row1.put("close", close);

		p = (BinomialModelPrediction) model.predict(row1);

		// System.out.println("Predicted value : " + p.label);

		return p.label;
	}

	// http://localhost:8085/getAutoSignalBinomial?sma100=13&sma50=12&macd=6&cci=9&lower_band=3&rsi=8&middle_band=4&momentum=14&signal_type=16&high=1&low=2&macd_signal=7&upper_band=5&dpercent=10&kpercent=11&close=15&open=0
	@RequestMapping(value = "/getAutoSignalBinomial", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getAutoSignalBinomial(

			@RequestParam(value = "open") Double open, @RequestParam(value = "high") Double high,
			@RequestParam(value = "low") Double low, @RequestParam(value = "lower_band") Double lower_band,
			@RequestParam(value = "middle_band") Double middle_band,
			@RequestParam(value = "upper_band") Double upper_band, @RequestParam(value = "macd") Double macd,
			@RequestParam(value = "macd_signal") Double macd_signal, @RequestParam(value = "rsi") Double rsi,
			@RequestParam(value = "cci") Double cci, @RequestParam(value = "dpercent") Double dpercent,
			@RequestParam(value = "kpercent") Double kpercent, @RequestParam(value = "sma50") Double sma50,
			@RequestParam(value = "sma100") Double sma100, @RequestParam(value = "momentum") Double momentum,
			@RequestParam(value = "close") Double close

	) throws Exception {

		BinomialModelPrediction p = null;
		RowData row1 = new RowData();

		row1.put("open", open);
		row1.put("high", high);
		row1.put("low", low);
		row1.put("lower_band", lower_band);
		row1.put("middle_band", middle_band);

		row1.put("upper_band", upper_band);
		row1.put("macd", macd);
		row1.put("macd_signal", macd_signal);
		row1.put("rsi", rsi);
		row1.put("cci", cci);
		row1.put("dpercent", dpercent);
		row1.put("kpercent", kpercent);
		row1.put("sma50", sma50);
		row1.put("sma100", sma100);
		row1.put("momentum", momentum);
		row1.put("close", close);
		row1.put("signal_type", "");

		MultinomialModelPrediction p1 = (MultinomialModelPrediction) autMLModel.predict(row1);

		p = (BinomialModelPrediction) autMLModel.predict(row1);

		System.out.println("Predicted value : " + p.label);

		return p.label;
	}

}
