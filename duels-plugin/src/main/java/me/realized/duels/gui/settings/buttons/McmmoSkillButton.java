package me.realized.duels.gui.settings.buttons;

import me.realized.duels.DuelsPlugin;
import me.realized.duels.gui.BaseButton;
import me.realized.duels.setting.Settings;
import me.realized.duels.util.inventory.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class McmmoSkillButton extends BaseButton {

    public McmmoSkillButton(final DuelsPlugin plugin) {
        super(plugin, ItemBuilder.of(Material.EXPERIENCE_BOTTLE).name(plugin.getLang().getMessage("GUI.settings.buttons.mcmmo-skill.name")).build());
    }

    @Override
    public void update(final Player player) {
        final Settings settings = settingManager.getSafely(player);
        final String mcmmoSkills = settings.isSkillsEnabled() ? lang.getMessage("GENERAL.enabled") : lang.getMessage("GENERAL.disabled");
        final String lore = plugin.getLang().getMessage("GUI.settings.buttons.mcmmo-skill.lore", "mcmmo_skills", mcmmoSkills);
        setLore(lore.split("\n"));
    }

    @Override
    public void onClick(final Player player) {
        final Settings settings = settingManager.getSafely(player);
        settings.setSkillsEnabled(!settings.isSkillsEnabled());
        settings.updateGui(player);
    }
}