package me.imystxc.whosonline;

import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.button.PlaceholderButton;
import ca.landonjw.gooeylibs2.api.button.linked.LinkType;
import ca.landonjw.gooeylibs2.api.button.linked.LinkedPageButton;
import ca.landonjw.gooeylibs2.api.helpers.PaginationHelper;
import ca.landonjw.gooeylibs2.api.page.GooeyPage;
import ca.landonjw.gooeylibs2.api.page.LinkedPage;
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate;
import com.pixelmonmod.pixelmon.config.PixelmonItems;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OnlineUI {

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
                .title(Utils.regex("&c&lOnline Players"));


        return PaginationHelper.createPagesFromPlaceholders(main, onlinePlayerButtons, page);
    }
}
