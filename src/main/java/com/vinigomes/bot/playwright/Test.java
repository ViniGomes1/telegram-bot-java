package com.vinigomes.bot.playwright;


public class Test {

	public static void main(String[] args) {
		PlaywrightMercadoLivre mlScrap = new PlaywrightMercadoLivre("https://meli.la/1RK6jY4");
		System.out.println(mlScrap.getDesconto());
		System.out.println(mlScrap.getImagemUrl());
		System.out.println(mlScrap.getTitulo());
		System.out.println(mlScrap.getPrecoAtual());
		System.out.println(mlScrap.getPrecoAnterior());
		System.out.println(mlScrap.getOpcoesExtra());
	}

}

/*
ngrok http 8080 antes de iniciar o server

*/