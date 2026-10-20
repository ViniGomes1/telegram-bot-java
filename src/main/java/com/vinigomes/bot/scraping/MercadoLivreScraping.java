package com.vinigomes.bot.scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MercadoLivreScraping {
	private String titulo;
	private String imagemUrl;
	private String precoAnterior;
	private String precoAtual;
	private String desconto;
	private String opcoesExtra;
	
	public MercadoLivreScraping(String link) {
		Document doc;
		try {
			doc = Jsoup.connect(link).get();
			Elements container = doc.select("section.rl-card-featured");
			this.imagemUrl = container.select("div.poly-card__portada > img").attr("src");
			this.titulo = container.select("div.poly-card__content > a.poly-component__title").text();
			
			this.precoAtual = container.select("div.poly-card__content > div.poly-content > div.poly-content__column > div.poly-component__price > div.poly-price__current > span.andes-money-amount").text();
			this.precoAnterior = verificarExistencia(container.select("div.poly-card__content > div.poly-content > div.poly-content__column > div.poly-component__price > s"));
			
			this.desconto = verificarExistencia(container.select("div.poly-card__content > div.poly-content > div.poly-content__column > div.poly-component__price > div.poly-price__current > span.poly-price__disc_label"));
			this.opcoesExtra = verificarExistencia(container.select("div.poly-card__content > div.poly-content > div.poly-content__column > div.poly-component__price > span.poly-price__installments"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public String verificarExistencia(Elements container) {
		if(container.isEmpty()) {
			return null;			
		}
		return container.text();
	}

	public String getTitulo() {
		return titulo;
	}

	public String getImagemUrl() {
		return imagemUrl;
	}

	public String getPrecoAnterior() {
		return precoAnterior;
	}

	public String getPrecoAtual() {
		return precoAtual;
	}

	public String getDesconto() {
		return desconto;
	}

	public String getOpcoesExtra() {
		return opcoesExtra;
	}
	
	
	
}
