package com.vinigomes.bot;

import java.io.IOException;

import org.htmlunit.FailingHttpStatusCodeException;
import org.htmlunit.WebClient;
import org.htmlunit.html.DomNode;
import org.htmlunit.html.DomNodeList;
import org.htmlunit.html.HtmlDivision;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlSpan;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TestHtmlunit {
	public static void main(String[] args) {
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		Document doc;
		try {
			HtmlPage page = client.getPage("https://a.co/d/0i4x6Vpw");
			doc = Jsoup.parse(page.asXml());
			
			System.out.println(doc.select("div#ppd > div#centerCol span#productTitle").text());
			client.close();
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
	}
}
