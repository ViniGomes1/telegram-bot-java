package com.vinigomes.bot;

import com.vinigomes.bot.telegram.CommandManager;

public class TestarComandos {

	public static void main(String[] args) {
		CommandManager comand = new CommandManager();
		System.out.println(comand.processar("/ping sei nada"));
	}

}
