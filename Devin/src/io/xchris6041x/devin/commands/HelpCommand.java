package io.xchris6041x.devin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.xchris6041x.devin.MessageSender;
import io.xchris6041x.devin.Validator;

public class HelpCommand implements CommandExecutor {

	private MessageSender msgSender;
	private String[] help;
	private int linesPerPage = 5;
	
	public HelpCommand(MessageSender msgSender, String[] help) {
		this.msgSender = msgSender;
		this.help = help;
	}

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		int page = 0;
		if(args.length > 0) {
			if(Validator.isInteger(args[0])) {
				page = Integer.parseInt(args[0]) - 1;
			}
		}
		
		int maxPage = (int) Math.ceil(page / (double) linesPerPage);
		if(page < 0) {
			page = 0;
		}
		else if(page > maxPage) {
			page = maxPage;
		}
		
		msgSender.info("Command Help (Page " + (page + 1) + ")");
		msgSender.info("--------------------------------------");
		for(int i = 0; i < linesPerPage; i++) {
			int index = page * linesPerPage + i;
			if(index >= help.length) {
				break;
			}
			
			msgSender.info(sender, help[i]);
		}
		return true;
	}
	
	/**
	 * @return the help text for each line.
	 */
	public String[] getHelpText() {
		return help;
	}
	
	/**
	 * The number of lines on each page.
	 * @return
	 */
	public int getLinesPerPage() { return linesPerPage; }
	public void setLinesPerPage(int linesPerPage) { this.linesPerPage = linesPerPage; }
	
}
