package me.imystxc.whosonline.UIs;

import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.button.PlaceholderButton;
import ca.landonjw.gooeylibs2.api.helpers.PaginationHelper;
import ca.landonjw.gooeylibs2.api.page.GooeyPage;
import ca.landonjw.gooeylibs2.api.page.LinkedPage;
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate;
import me.imystxc.whosonline.Utils.Utils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.server.FMLServerHandler;

import java.util.*;

public class StaffOnlyUI {

    public static ItemStack getPlayerHead(EntityPlayerMP player) {
        ItemStack playerHead = new ItemStack(Items.SKULL, 1, 3);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("SkullOwner", new NBTTagString(player.getName()));
        playerHead.setTagCompound(nbt);
        return playerHead;
    }

    public static String getPlayerPrefix(LuckPerms luckPerms, UUID uuid) {
        User user = luckPerms.getUserManager().getUser(uuid);
        String prefix = user.getCachedData().getMetaData().getPrefix();
        return  prefix;
    }

    public static List<Button> getOnlinePlayerButton() {

        List<Button> onlinePlayerButtons = new ArrayList<>();
        PlayerList list = FMLServerHandler.instance().getServer().getPlayerList();
        LuckPerms luckPerms = LuckPermsProvider.get();

        for (EntityPlayerMP players : list.getPlayers()) {
            UUID uuid = UUID.fromString(players.getCachedUniqueIdString());
            if(players.canUseCommand(0, "stafflist")) {
                onlinePlayerButtons.add(GooeyButton.builder()
                        .display(getPlayerHead(players))
                        .title(TextFormatting.GOLD + "" + TextFormatting.BOLD + (players.getName()))
                        .lore(Collections.singletonList(Utils.regex("&7Postion: " + getPlayerPrefix(luckPerms, uuid))))
                        .build());
            }
        }
        return onlinePlayerButtons;
    }

    public static GooeyPage menu(List<Button> onlinePlayerButtons) {

        PlaceholderButton placeholder = new PlaceholderButton();

        ChestTemplate main = ChestTemplate.builder(5)
                .row(0, Utils.colouredPane(EnumDyeColor.RED))
                .row(4, Utils.colouredPane(EnumDyeColor.WHITE))
                .set(1,0, Utils.colouredPane(EnumDyeColor.RED))
                .set(1,8, Utils.colouredPane(EnumDyeColor.RED))
                .set(2,0, Utils.colouredPane(EnumDyeColor.BLACK))
                .set(2,8, Utils.colouredPane(EnumDyeColor.BLACK))
                .set(3,0, Utils.colouredPane(EnumDyeColor.WHITE))
                .set(3,8, Utils.colouredPane(EnumDyeColor.WHITE))
                .fill(placeholder)
                .set(4, 0 , Utils.prevButton())
                .set(4, 8 , Utils.nextButton())
                .build();

        LinkedPage.Builder page = LinkedPage.builder()
                .title(Utils.regex("&6&lOnline Staff Members"));


        return PaginationHelper.createPagesFromPlaceholders(main, onlinePlayerButtons, page);
    }
}

