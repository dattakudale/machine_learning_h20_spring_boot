package com.dk;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import hex.genmodel.ModelMojoReader;
import hex.genmodel.MojoReaderBackend;
import hex.genmodel.MojoReaderBackendFactory;
import hex.genmodel.MojoReaderBackendFactory.CachingStrategy;
import hex.genmodel.algos.ensemble.StackedEnsembleMojoReader;
import hex.genmodel.easy.EasyPredictModelWrapper;

@SpringBootApplication
public class MachineLearningAlgoApplication {

	/*
	 * 
	 * open high low lower_band middle_band upper_band macd macd_signal rsi cci
	 * dpercent kpercent sma50 sma100 momentum close signal_type
	 */

	@Bean(name = "GBM")
	public EasyPredictModelWrapper getGBMModel() {

		EasyPredictModelWrapper model = null;
		try {

			ClassPathResource resource = new ClassPathResource("gbm_0e067bc4_7c5f_4c06_9e27_3916c2aef8aa.zip");

			MojoReaderBackend reader = MojoReaderBackendFactory.createReaderBackend(resource.getURL(),
					CachingStrategy.MEMORY);
			model = new EasyPredictModelWrapper(ModelMojoReader.readFrom(reader));

			// model = new EasyPredictModelWrapper(
			// MojoModel.load("c:/tmp/glm_e393d2ea_d30b_4375_8b72_a0c188600c0a.zip"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return model;
	}

	/*
	 * {sma100=13, sma50=12, macd=6, cci=9, lower_band=3, rsi=8, middle_band=4,
	 * momentum=14, signal_type=16, high=1, low=2, macd_signal=7, upper_band=5,
	 * dpercent=10, kpercent=11, close=15, open=0}
	 */
	@Bean(name = "AutoMLStackedModel")
	public EasyPredictModelWrapper getAutoMLStackedModel() {

		EasyPredictModelWrapper model = null;
		try {

			ClassPathResource resource = new ClassPathResource(
					"StackedEnsemble_BestOfFamily_0_AutoML_20180203_205824.zip");

			MojoReaderBackend reader = MojoReaderBackendFactory.createReaderBackend(resource.getURL(),
					CachingStrategy.MEMORY);
			model = new EasyPredictModelWrapper(StackedEnsembleMojoReader.readFrom(reader));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return model;
	}

	public static void main(String[] args) {
		SpringApplication.run(MachineLearningAlgoApplication.class, args);
	}
}
