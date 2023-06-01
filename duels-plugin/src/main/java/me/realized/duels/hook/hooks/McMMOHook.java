package me.realized.duels.hook.hooks;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import me.realized.duels.DuelsPlugin;
import me.realized.duels.config.Config;
import me.realized.duels.util.hook.PluginHook;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public class McMMOHook extends PluginHook<DuelsPlugin> {

    public static final String NAME = "mcMMO";
    private static final String PERMISSION = "mcmmo.skills.*";

    private final Config config;
    private final Map<UUID, PermissionAttachment> attachments = new HashMap<>();
    private final Map<UUID, PermissionAttachment> attachments2 = new HashMap<>();

    public McMMOHook(final DuelsPlugin plugin) {
        super(plugin, NAME);
        this.config = plugin.getConfiguration();
    }

    public void disableSkills(final Player player) {
        if (!config.isDisableSkills()) {
            return;
        }

        final PermissionAttachment attachment = attachments.computeIfAbsent(player.getUniqueId(), result -> player.addAttachment(plugin));
        attachment.setPermission(PERMISSION, false);
        player.recalculatePermissions();
    }

    public void enableSkills(final Player player) {
        if (!config.isDisableSkills()) {
            return;
        }

        final PermissionAttachment attachment = attachments.remove(player.getUniqueId());

        if (attachment == null) {
            return;
        }

        attachment.remove();
    }

    public void enableSkillAfterMatch(final Player player) {
        final PermissionAttachment attachment = attachments2.remove(player.getUniqueId());
        if (attachment != null) {
            attachment.remove();
        }
    }

    public void disableSkillBedforeMatch(final Player player) {
        final PermissionAttachment attachment = attachments2.computeIfAbsent(player.getUniqueId(), result -> player.addAttachment(plugin));
        attachment.setPermission(PERMISSION, false);
        player.recalculatePermissions();
    }
}
