package com.vinigomes.bot.response;

import java.util.HashMap;
import java.util.Map;

import com.pengrad.telegrambot.request.BaseRequest;
import com.vinigomes.bot.telegram.CommandManager;

public class ResponseManager {
	private final Map<String, ResponseAction> respostas = new HashMap<>();
	
	public ResponseManager() {
		respostas.put("imagem", new ResponsePhoto());
		respostas.put("mensagem", new ResponseMessage());
	}
	
	public BaseRequest<?, ?> processarMensagem(String entrada, Long chatId, String mensagem) {
		BaseRequest<?, ?> resultado;
		if (entrada == null) return null;
		
		String[] partes = entrada.split("\\s+", 2);
		ResponseAction acao = respostas.get(partes[0].trim().toLowerCase());
		
		if(acao != null) {
			resultado = acao.executar(chatId, mensagem);
		}else {
			return null;
		}
		return resultado;
	}
	public BaseRequest<?, ?> processarImagem(String entrada, Long chatId, String mensagem) {
		CommandManager comands = new CommandManager();
		Map<String, String> comandoProcessar = comands.processar(mensagem);
		
		BaseRequest<?, ?> resultado;
		if (entrada == null) return null;
		
		String[] partes = entrada.split("\\s+", 2);
		ResponseAction acao = respostas.get(partes[0].trim().toLowerCase());
		
		if(acao != null) {
			resultado = acao.executar(chatId, comandoProcessar);
		}else {
			return null;
		}
		return resultado;
	}
}
