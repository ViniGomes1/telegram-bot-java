package com.vinigomes.bot.response;

import java.util.Map;

import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;

public class ResponseMessage implements ResponseAction {


	@Override
	public BaseRequest<?, ?> executar(Long chatId, String mensagem) {
		return new SendMessage(chatId, mensagem);
	}

	@Override
	public BaseRequest<?, ?> executar(Long chatId, Map<String, String> resultado) {
		// TODO Auto-generated method stub
		return null;
	}

}
