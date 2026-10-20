package com.vinigomes.bot.scraping;

public class TestScraping {
	public static void main(String[] args) {
		MercadoLivreScraping mlScrap = new MercadoLivreScraping("https://meli.la/19LJAB2");
		System.out.println(mlScrap.getTitulo());
		System.out.println(mlScrap.getImagemUrl());
		System.out.println(mlScrap.getPrecoAtual());
		System.out.println(mlScrap.getPrecoAnterior());
		System.out.println(mlScrap.getDesconto());
		System.out.println(mlScrap.getOpcoesExtra());
		
	}
	
}
