package io.xchris6041x.devin.commands;

import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.xchris6041x.devin.MessageSender;
import io.xchris6041x.devin.Validator;

@CommandOptions(parameters = "<page>")
public class HelpCommand implements CommandExecutor {

	private LayeredCommandExecutor lce;
	private String cmdLabel;
	private MessageSender msgSender;
	private int linesPerPage = 7;
	
	public HelpCommand(LayeredCommandExecutor lce, MessageSender msgSender, String cmdLabel) {
		this.lce = lce;
		this.msgSender = msgSender;
		this.cmdLabel = cmdLabel;
	}

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		int page = 0;
		if(args.length > 0) {
			if(Validator.isInteger(args[0])) {
				page = Integer.parseInt(args[0]) - 1;
			}
		}
		
		
		// Build Command
		List<String> help = new ArrayList<String>();
		buildList(sender, help, cmdLabel, lce);
		
		int maxPage = (int) Math.ceil(help.size() / (double) linesPerPage);
		if(page < 0) {
			page = 0;
		}
		else if(page > maxPage) {
			page = maxPage;
		}
		
		msgSender.info(sender, cmdLabel.toUpperCase() + " Command Help (Page " + (page + 1) + ")");
		for(int i = 0; i < linesPerPage; i++) {
			int index = page * linesPerPage + i;
			if(help.size() <= index) break;
			
			msgSender.info(sender, help.get(index));
		}
		
		return true;
	}
	private void buildList(CommandSender sender, List<String> help, String label, LayeredCommandExecutor lce) {
		if(lce.getExecutor() != null) {
			CommandOptions co = lce.getExecutor().getClass().getAnnotation(CommandOptions.class);
			if(co == null) {
				help.add("/" + label + ": Unknown parameters and description.");
			}
			else {
				if((!co.onlyPlayers() || (sender instanceof Player)) && (!co.onlyOps() || sender.isOp()) && (co.permission().equals("[NULL]") || sender.hasPermission(co.permission())))
				{
					help.add("/" + label + " " + co.parameters());
				}
			}
		}
		
		for(Entry<String, LayeredCommandExecutor> layer : lce.getLayerMap().entrySet()) {
			buildList(sender, help, label + " " + layer.getKey(), layer.getValue());
		}
	}
	
	/**
	 * The number of lines on each page.
	 * @return
	 */
	public int getLinesPerPage() { return linesPerPage; }
	public void setLinesPerPage(int linesPerPage) { this.linesPerPage = linesPerPage; }
	
}
