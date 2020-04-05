package me.time6628.clag.sponge.runnables;

import me.time6628.clag.sponge.CatClearLag;
import me.time6628.clag.sponge.Messages;
import org.spongepowered.api.boss.BossBarOverlays;
import org.spongepowered.api.boss.ServerBossBar;
import org.spongepowered.api.effect.sound.SoundCategories;
import org.spongepowered.api.effect.sound.SoundTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.scheduler.SpongeExecutorService;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.chat.ChatTypes;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Created by TimeTheCat on 7/20/2016.
 */
public class ItemClearingWarning implements Runnable {

    public static ServerBossBar bossBar;
    public static SpongeExecutorService.SpongeFuture<?> bossBarUpdater;

    private final int seconds;
    private final CatClearLag plugin = CatClearLag.instance;

    public ItemClearingWarning(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public void run() {
        Text message = Messages.getWarningMsg(seconds);
        Text rawMessage = Messages.getWarningMsg(seconds, false);
        Collection<Player> onlinePlayers = plugin.getGame().getServer().getOnlinePlayers();
        plugin.getGame().getServer().getBroadcastChannel().send(message);
    }
}
