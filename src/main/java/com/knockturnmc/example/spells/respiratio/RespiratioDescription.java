package com.knockturnmc.example.spells.respiratio;

import com.knockturnmc.spellapi.player.MagicPlayer;
import com.knockturnmc.spellapi.spell.SpellDescription;
import org.bukkit.ChatColor;

public class RespiratioDescription extends SpellDescription {

    @Override
    public String buildDescription(MagicPlayer magicPlayer, boolean b) {
        return ChatColor.GRAY + "Respiratio will grand you water breathing for "
                + getScaling("duration", magicPlayer, b) + ChatColor.GRAY + " seconds";
    }
}
