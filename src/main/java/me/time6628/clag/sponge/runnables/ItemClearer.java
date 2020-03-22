package me.time6628.clag.sponge.runnables;

import me.time6628.clag.sponge.CatClearLag;
import me.time6628.clag.sponge.Messages;
import me.time6628.clag.sponge.utils.EntityHelpers;
import org.spongepowered.api.effect.sound.SoundCategories;
import org.spongepowered.api.effect.sound.SoundTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.chat.ChatTypes;

import java.util.concurrent.TimeUnit;

/**
 * Created by pturc_000 on 7/13/2016.
 */
public class ItemClearer implements Runnable {
    private final CatClearLag plugin = CatClearLag.instance;

    @Override
    public void run() {
        int i = EntityHelpers.clearGroundItems();
        Text message = Messages.getClearMsg(i);
        Text messageRaw = Messages.getClearMsg(i, false);
        ItemClearingWarning.bossBar.setName(messageRaw);
        ItemClearingWarning.bossBar.setPercent(0.0f);
        plugin.getGame().getServer().getBroadcastChannel().send(message);
        ItemClearingWarning.bossBarUpdater.cancel(true);
        plugin.getGame().getScheduler().createTaskBuilder().execute(() -> {
            //plugin.getLogger().info("bossBarPercent {}", ItemClearingWarning.bossBar.getPercent());
            if (ItemClearingWarning.bossBar.getPercent() == 0.0f) {
                ItemClearingWarning.bossBar.setVisible(false);
                ItemClearingWarning.bossBar = null;
            }
        }).delay(plugin.getCclConfig().bossBar.hideBoss, TimeUnit.SECONDS).async().submit(plugin);
    }
}
