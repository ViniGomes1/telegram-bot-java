package com.vinigomes.bot;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TestJsup {

	public static void main(String[] args) {
		Document doc;

		try {
			// fetching the target website
			
			doc = Jsoup 
					.connect("https://meli.la/19LJAB2")
					.get();
			
			System.out.println(doc.select("section.rl-card-featured"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
