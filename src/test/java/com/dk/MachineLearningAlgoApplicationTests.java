package com.dk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MachineLearningAlgoApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private List<TradeDTO> list = new ArrayList<>();

	@Before
	public void setup() throws IOException {

		ClassPathResource resource = new ClassPathResource("trade_export.csv");

		Scanner sc = null;
		try {
			sc = new Scanner(resource.getFile());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (sc.hasNext()) {

			String line = sc.next();

			if (line != null && line.contains("open")) {
				continue;
			}

			String[] data = line.split(",");

			// open high low close signal_type lower_band middle_band upper_band macd
			// macd_signal rsi cci dpercent kpercent sma50 sma100 momentum

			TradeDTO trade = new TradeDTO();
			trade.setOpen(Double.valueOf(data[0]));
			trade.setHigh(Double.valueOf(data[1]));
			trade.setLow(Double.valueOf(data[2]));
			trade.setClose(Double.valueOf(data[3]));
			trade.setSignalType(data[4]);
			trade.setLower_band(Double.valueOf(data[5]));
			trade.setMiddle_band(Double.valueOf(data[6]));
			trade.setUpper_band(Double.valueOf(data[7]));
			trade.setMacd(Double.valueOf(data[8]));
			trade.setMacd_signal(Double.valueOf(data[9]));
			try {
				trade.setRsi(Double.valueOf(data[10]));
			} catch (Exception e) {
				trade.setRsi(0.0d);
			}
			trade.setCci(Double.valueOf(data[11]));
			trade.setDpercent(Double.valueOf(data[12]));
			trade.setKpercent(Double.valueOf(data[13]));
			trade.setSma50(Double.valueOf(data[14]));
			trade.setSma100(Double.valueOf(data[15]));
			trade.setMomentum(Double.valueOf(data[16]));

			list.add(trade);
		}
	}

	@Test
	public void test1() throws Exception {

		Double total = 0d;

		Double buyValue = 0d;
		int i = 0;
		for (TradeDTO trade : list) {

			String value = this.restTemplate.getForObject(
					"http://localhost:" + port + "/getSignalBinomial?" + trade.toURLString(), String.class);

			if (value.equals("BUY")) {

				if (buyValue > 0) {

					// HOLD
					System.out.println("HOLD");

				} else {

					buyValue = trade.getClose();

				}

			} else if (value.equals("SELL")) {

				if (buyValue > 0) {
					System.out.println("SELL profit = " + (trade.getClose() - buyValue));
					total += (trade.getClose() - buyValue);
					buyValue = 0d;

				}
			}

			/*
			 * if(! trade.getSignalType().equals(value) ) { System.out.println((i++ )
			 * +" Actual Value : "+ trade.getSignalType() + " = " + value); }else { i++; }
			 */

			// assertEquals(trade.getSignalType(), value);

		}

		System.out.println("total =" + total);

	}

}
