package ch.hatbe2113.vibeeconomy.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ch.hatbe2113.vibeeconomy.io.CustomConfigHandler;
import ch.hatbe2113.vibeeconomy.io.TextOutput;
import ch.hatbe2113.vibeeconomy.main.Main;

public class BankCommand implements CommandExecutor {
	
	private Main main;
	private CustomConfigHandler lang;
	
	public BankCommand(Main main) {
		this.main = main;
		this.lang = main.getLang();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		// Allow Only Player with permission, console and commandblock
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(!p.hasPermission("vibecoin.admin")) {
				TextOutput.outputToPlayer(p, this.lang.getString("errors.noPerms"));		
				return false;
			}
		}
		
		if(args.length == 2) {
			if(args[0].equalsIgnoreCase("balance")) {
				new BankBalanceCommand(this.main, sender, args);
				return false;
			}
		} else if (args.length == 3) {
			if(args[0].equalsIgnoreCase("set")) {
				new BankSetCommand(this.main, sender, args);
				return false;
			} else if(args[0].equalsIgnoreCase("add")) {
				new BankAddCommand(this.main, sender, args);
				return false;
			} else if(args[0].equalsIgnoreCase("remove")) {
				new BankRemoveCommand(this.main, sender, args);
				return false;
			}
		}
		
		TextOutput.outputToCommandSender(sender, this.lang.getString("errors.wrongFormat")
				.replace("{USAGE}", "/" + label + " [set/add/remove] <user> <amount>"));		
		return false;
	}
}
