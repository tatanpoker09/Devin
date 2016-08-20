package io.xchris6041x.devin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

/**
 * A class for sending messages to players and consoles, with info and error headers.
 * @author Christopher Bishop
 */
public class MessageSender {

	private String headerInfo;
	private String headerError;
	
	public MessageSender() {
		this("", "");
	}
	public MessageSender(String headerInfo, String headerError) {
		this.headerInfo = headerInfo;
		this.headerError = headerError;
	}
	
	/**
	 * Send message to command sender.
	 * @param sender - The recipient of the message.
	 * @param message - The message to send.
	 */
	public void info(CommandSender sender, String message) {
		sender.sendMessage(headerInfo + message);
	}
	
	/**
	 * Send error message to commands sender.
	 * @param sender - The recipient of the message.
	 * @param message - The message to send.
	 */
	public void error(CommandSender sender, String message) {
		sender.sendMessage(headerError + message);
	}
	
	/**
	 * Send message to console.
	 * @param message - The message to send.
	 */
	public void info(String message) {
		info(Bukkit.getConsoleSender(), message);
	}
	/**
	 * Send error to console.
	 * @param message - The message to send.
	 */
	public void error(String message) {
		error(Bukkit.getConsoleSender(), message);
	}
	
}
