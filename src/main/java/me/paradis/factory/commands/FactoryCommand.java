package me.paradis.factory.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Syntax;
import me.paradis.factory.tools.ItemBuilder;
import me.paradis.factory.tools.MessReplacer;

@CommandAlias("factory")
//@CommandPermission("factory.admin")
public class FactoryCommand extends BaseCommand {
    
    @HelpCommand
    @Syntax("")
    @Description("Displays help for Factory")
    public void onHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    @Default
    @CatchUnknown
    public void onDefault(Player player){
        MessReplacer.replace("&cUnknown command. Type /factory help for help.", player);
    }

    @Subcommand("get")
    public class getCommand extends BaseCommand {
        @Subcommand("hopper")
        public void onGetHopper(Player player){
            player.getInventory().addItem(new ItemBuilder(Material.HOPPER).name("factory hopper").build());
        }
    }


}
