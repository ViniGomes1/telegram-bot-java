package com.vinigomes.bot.telegram;

import java.util.HashMap;
import java.util.Map;

import com.vinigomes.bot.telegram.commands.MercadoCommand;
import com.vinigomes.bot.telegram.commands.TestCommand;

public class CommandManager {
	private final Map<String, CommandActions> comandos = new HashMap<>();
	
	public CommandManager() {
		comandos.put("/ping", new TestCommand());
		comandos.put("/mercado", new MercadoCommand());
	}
	
	public Map<String, String> processar(String entrada) {
		 Map<String, String> resultado = new HashMap<>();
		if (entrada == null) resultado.put("resultado", "Sem comando");
		String[] partes = entrada.split("\\s+", 2);
		CommandActions acao = comandos.get(partes[0].trim().toLowerCase());
		if(acao != null) {
			resultado = acao.execute(partes[1]);
		}else {
			resultado.put("erro", "Comando não encontrado");
		}
		return resultado;
	}
}
