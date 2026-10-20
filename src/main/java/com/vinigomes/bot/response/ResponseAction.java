package com.vinigomes.bot.response;

import java.util.Map;

import com.pengrad.telegrambot.request.BaseRequest;

public interface ResponseAction {
	BaseRequest<?, ?> executar(Long chatId, Map<String, String> resultado);
	BaseRequest<?, ?> executar(Long chatId, String mensagem);
}
