package com.crazymoose.paperdupe;

import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.BookUpdateC2SPacket;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.screen.sync.ItemStackHash;
import net.minecraft.text.Text;

import java.util.List;
import java.util.Optional;

public class PaperDupe extends Module {

    public PaperDupe() {
        super(Main.CATEGORY, "paper-dupe", "Paper 1.20.4-1.21.1, requires writable book. Relog before use");
    }

    @EventHandler
    private void onTick(TickEvent.Post event) {
        if(!(mc.player.getInventory().getSelectedStack().getItem()  == Items.WRITABLE_BOOK)) {
            mc.player.sendMessage(Text.of("Please hold a writable book!"), true);
            toggle();
            return;
        }
        for (int i = 9; i < 44; i++) {
            if (36 + mc.player.getInventory().getSelectedSlot() == i) continue;
            mc.player.networkHandler.sendPacket(new ClickSlotC2SPacket(
                mc.player.currentScreenHandler.syncId,
                mc.player.currentScreenHandler.getRevision(),
                (short) i,
                (byte) 1,
                SlotActionType.THROW,
                Int2ObjectMaps.emptyMap(),
                ItemStackHash.EMPTY
            ));
        }
        mc.player.networkHandler.sendPacket(new BookUpdateC2SPacket(
            mc.player.getInventory().getSelectedSlot(), List.of(""), Optional.of("The quick brown fox jumps over the lazy dog"
        )));
        toggle();
    }
}
