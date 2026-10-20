package com.vinigomes.bot.telegram.commands;

import java.util.HashMap;
import java.util.Map;

import com.vinigomes.bot.scraping.MercadoLivreScraping;
import com.vinigomes.bot.telegram.CommandActions;

public class MercadoCommand implements CommandActions{

	@Override
	public Map<String, String>  execute(String link) {
		 Map<String, String> resultado = new HashMap<>();
		 
		MercadoLivreScraping mlScrap = new MercadoLivreScraping(link);
		String retorno = "<b>" + mlScrap.getTitulo() + "</b>" + "\n\n"
				.concat(mlScrap.getPrecoAnterior() != null ? "DE: " + "<s> " + mlScrap.getPrecoAnterior() + "</s>" + "\n": "")
				.concat(mlScrap.getPrecoAnterior() != null ? "POR: " + mlScrap.getPrecoAtual() + "": "APENAS: " + mlScrap.getPrecoAtual())
				.concat(mlScrap.getDesconto() != null ? " <b>" + mlScrap.getDesconto() + "</b> \n" : "\n")
				.concat(mlScrap.getOpcoesExtra() != null ? mlScrap.getOpcoesExtra() + "\n": "") + "\n"
				.concat("ACESSANDO PELO LINK: " + link);
		resultado.put("mensagem", retorno);
		resultado.put("foto", mlScrap.getImagemUrl());
		return resultado;
	}

}
