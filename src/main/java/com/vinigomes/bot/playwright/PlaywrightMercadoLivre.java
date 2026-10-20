package com.vinigomes.bot.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightMercadoLivre {
	private String titulo;
	private String imagemUrl;
	private String precoAnterior;
	private String precoAtual;
	private String desconto;
	private String opcoesExtra;

	public PlaywrightMercadoLivre(String link) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.webkit().launch();
			BrowserContext context = browser.newContext(new Browser.NewContextOptions().setJavaScriptEnabled(false));
			Page page = context.newPage();
			page.navigate(link);
			this.titulo = page.locator("div.poly-card__content > a").first().textContent();// titulo
			this.imagemUrl = page.locator("div.poly-card__portada > img").first().getAttribute("src"); // imagem url
			Locator locator = page.locator(
					"div.poly-card__content > div.poly-content > div.poly-content__column div.poly-component__price")
					.first();
			this.precoAnterior = locatorVariaveis(locator.locator("s.andes-money-amount--previous").first());// preco
																												// anterior
			this.precoAtual = locator.locator("div.poly-price__current > span.andes-money-amount").textContent();// preco
																													// atual
			this.desconto = locatorVariaveis(
					locator.locator("div.poly-price__current > span.poly-price__disc_label").first());// desconto
			this.opcoesExtra = locatorVariaveis(locator.locator("span.poly-price__installments").first());// opções extras

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String locatorVariaveis(Locator precoanterior) {
		if (precoanterior.count() < 1) {
			return null;
		}
		return precoanterior.first().textContent();
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
