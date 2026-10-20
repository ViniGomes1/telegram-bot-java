package com.vinigomes.bot;

import java.util.HashMap;
import java.util.Map;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.request.SetWebhook;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.utility.BotUtils;
import com.vinigomes.bot.telegram.CommandManager;

import io.javalin.Javalin;

public class App {

	private static final String BOT_TOKEN = System.getenv("BOT_TOKEN");
	// URL HTTPS pública (ex: gerada pelo ngrok ou seu servidor com SSL)
	private static final String PUBLIC_URL = System.getenv("PUBLIC_URL");
	
	public static void main(String[] args) {
		TelegramBot bot = new TelegramBot(BOT_TOKEN);
		int porta = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
		// 1. Cria a aplicação no Javalin 7 com as rotas no bloco de configuração
		Javalin app = Javalin.create(config -> {
			// Define o endpoint POST no roteador do Javalin 7
			config.routes.post("/webhook", ctx -> {
				String payload = ctx.body();
				CommandManager comands = new CommandManager();
				// Faz o parse do JSON do Telegram usando o utilitário do Pengrad
				Update update = BotUtils.parseUpdate(payload);
				
				//algo
				if (update.message() != null && update.message().text() != null) {
					//e necessario entrar aqui pois verifica se há algo, if obrigatório
					
					long chatId = update.message().chat().id();
					String textoRecebido = update.message().text();
					
					Map<String, String> resultado = comands.processar(textoRecebido);
					// Envia a resposta de volta ao usuário
					bot.execute(new SendPhoto(chatId, resultado.get("foto")).caption(resultado.get("mensagem")).parseMode(ParseMode.HTML));//new SendMessage(chatId, resposta).parseMode(ParseMode.HTML)
				}

				// Responde 200 OK para o Telegram saber que a mensagem foi entregue
				ctx.status(200).result("OK");
			});
		}).start(porta); // Inicia o servidor na porta 8080

		// 2. Registra a URL do webhook no Telegram automaticamente ao iniciar
		BaseResponse response = bot.execute(new SetWebhook().url(PUBLIC_URL + "/webhook"));
		if (response.isOk()) {
			System.out.println("Webhook registrado com sucesso no Telegram!");
		} else {
			System.err.println("Falha ao registrar webhook: " + response.description());
		}
	}

}

/*
ngrok http 8080 antes de iniciar o server

*/
