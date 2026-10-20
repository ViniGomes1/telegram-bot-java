package com.vinigomes.bot.response;

import java.util.Map;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.SendResponse;

public class ResponsePhoto implements ResponseAction {

	@Override
	public BaseRequest<SendPhoto, SendResponse> executar(Long chatId, Map<String, String> resultado) {
		return new SendPhoto(chatId, resultado.get("foto")).caption(resultado.get("mensagem")).parseMode(ParseMode.HTML);
	}

	@Override
	public BaseRequest<?, ?> executar(Long chatId, String mensagem) {
		// TODO Auto-generated method stub
		return null;
	}

}
