package me.imystxc.whosonline;

import ca.landonjw.gooeylibs2.api.UIManager;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class Command extends CommandBase {
    @Override
    public String getName() {
        return "online";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/online";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1) {
            if (!(sender instanceof EntityPlayerMP)) {
                sender.sendMessage(new TextComponentString("only-players"));
                return;
            }
            EntityPlayerMP player = (EntityPlayerMP) sender;
            UIManager.openUIForcefully(player, OnlineUI.menu(OnlineUI.getOnlinePlayerButton(player)));
        }
    }
}