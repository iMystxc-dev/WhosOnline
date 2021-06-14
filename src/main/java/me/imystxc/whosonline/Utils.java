package me.imystxc.whosonline;

import ca.landonjw.gooeylibs2.api.UIManager;
import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.button.linked.LinkType;
import ca.landonjw.gooeylibs2.api.button.linked.LinkedPageButton;
import com.pixelmonmod.pixelmon.config.PixelmonItems;
import com.pixelmonmod.pixelmon.config.PixelmonItemsHeld;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static String regex(String line) {
        String regex = "&(?=[0123456789abcdefklmnor])";
        Pattern pattern = Pattern.compile(regex, 2);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            line = line.replaceAll(regex, "§");
        }

        return line;
    }

    public static LinkedPageButton prevButton() {
        return LinkedPageButton.builder()
                .display((new ItemStack(PixelmonItems.LtradeHolderLeft)))
                .title(TextFormatting.DARK_AQUA + ("Previous Page"))
                .linkType(LinkType.Previous)
                .build();
    }

    public static LinkedPageButton nextButton() {
        return LinkedPageButton.builder()
                .display((new ItemStack(PixelmonItems.tradeHolderRight)))
                .title(TextFormatting.DARK_AQUA + ("Next Page"))
                .linkType(LinkType.Next)
                .build();
    }

    public static GooeyButton backButton() {
        return GooeyButton.builder()
                .display((new ItemStack(PixelmonItemsHeld.ejectButton)))
                .title(TextFormatting.RED + ("Back"))
                .onClick(buttonAction -> {
                    EntityPlayerMP player = buttonAction.getPlayer();;
                    UIManager.openUIForcefully(player, MainUI.menu(player));
                })
                .build();
    }

    public static Button colouredPane(EnumDyeColor value) {
        return GooeyButton.builder()
                .display(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, value.getMetadata()))
                .title("")
                .build();
    }
}
