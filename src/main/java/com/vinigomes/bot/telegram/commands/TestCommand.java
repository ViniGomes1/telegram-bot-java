package com.vinigomes.bot.telegram.commands;

import java.util.HashMap;
import java.util.Map;

import com.vinigomes.bot.telegram.CommandActions;

public class TestCommand implements CommandActions {

	@Override
	public Map<String, String> execute(String link) {
		 Map<String, String> resultado = new HashMap<>();
		 resultado.put("teste", "teste");
		return resultado;
	}
	
}
