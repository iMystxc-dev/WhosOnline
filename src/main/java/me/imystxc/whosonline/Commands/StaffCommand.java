package me.imystxc.whosonline.Commands;

import ca.landonjw.gooeylibs2.api.UIManager;
import me.imystxc.whosonline.UIs.StaffOnlyUI;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.util.UUID;

public class StaffCommand extends CommandBase {
    @Override
    public String getName() {
        return "staff";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/staff";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1) {
            if (!(sender instanceof EntityPlayerMP)) {
                sender.sendMessage(new TextComponentString("only-players"));
                return;
            }
            EntityPlayerMP player = (EntityPlayerMP) sender;
            LuckPerms luckPerms = LuckPermsProvider.get();
            UUID uuid = UUID.fromString(player.getCachedUniqueIdString());
            UIManager.openUIForcefully(player, StaffOnlyUI.menu(StaffOnlyUI.getOnlinePlayerButton()));
        }
    }
}