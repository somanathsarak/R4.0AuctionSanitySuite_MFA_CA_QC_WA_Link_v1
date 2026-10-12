package com.wci.auction.org.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigDataProvider {

	public Properties Pro;

	public ConfigDataProvider() throws IOException {
		File src = new File(System.getProperty("user.dir") + "/Configuration/config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			Pro = new Properties();
			Pro.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}