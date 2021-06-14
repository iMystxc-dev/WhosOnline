package me.imystxc.whosonline;

import ca.landonjw.gooeylibs2.api.UIManager;
import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.button.PlaceholderButton;
import ca.landonjw.gooeylibs2.api.helpers.PaginationHelper;
import ca.landonjw.gooeylibs2.api.page.GooeyPage;
import ca.landonjw.gooeylibs2.api.page.LinkedPage;
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate;
import com.pixelmonmod.pixelmon.config.PixelmonItems;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.server.FMLServerHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainUI {

    public static ItemStack getPlayerHead(EntityPlayerMP player) {
        ItemStack playerHead = new ItemStack(Items.SKULL, 1, 3);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("SkullOwner", new NBTTagString(player.getName()));
        playerHead.setTagCompound(nbt);
        return playerHead;
    }

    public static List<Button> getOnlinePlayerButton(EntityPlayerMP player) {

        List<Button> onlinePlayerButtons = new ArrayList<>();
        PlayerList list = FMLServerHandler.instance().getServer().getPlayerList();

        for (EntityPlayerMP players : list.getPlayers()) {
            onlinePlayerButtons.add(GooeyButton.builder()
                    .display(getPlayerHead(players))
                    .title(TextFormatting.GOLD + "" + TextFormatting.BOLD + (players.getName()))
                    .build());
        }
        return onlinePlayerButtons;
    }

    public static GooeyPage menu(EntityPlayerMP player) {

        GooeyButton players = GooeyButton.builder()
                .display(new ItemStack(PixelmonItems.hourglassSilver))
                .title(Utils.regex("&e&lOnline Players"))
                .onClick(buttonAction -> {
                    UIManager.openUIForcefully(player, OnlineUI.menu(OnlineUI.getOnlinePlayerButton(player)));
                })
                .build();

        GooeyButton staff = GooeyButton.builder()
                .display(new ItemStack(PixelmonItems.hourglassGold))
                .title(Utils.regex("&e&lStaff Members"))
                .onClick(buttonAction -> {
                    UIManager.openUIForcefully(player, StaffUI.menu(StaffUI.getOnlinePlayerButton()));
                })
                .build();

        ChestTemplate main = ChestTemplate.builder(3)
                .row(0, Utils.colouredPane(EnumDyeColor.RED))
                .row(1,Utils.colouredPane(EnumDyeColor.BLACK))
                .row(2, Utils.colouredPane(EnumDyeColor.WHITE))
                .set(1, 2, players)
                .set(1, 6, staff)
                .build();

        return LinkedPage.builder()
                .template(main)
                .title(Utils.regex("&c&lOnline Players"))
                .build();
    }
}
