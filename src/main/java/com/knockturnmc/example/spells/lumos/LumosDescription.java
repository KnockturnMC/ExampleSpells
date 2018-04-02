package com.knockturnmc.example.spells.lumos;

import com.knockturnmc.spellapi.player.MagicPlayer;
import com.knockturnmc.spellapi.spell.SpellDescription;
import org.bukkit.ChatColor;

public class LumosDescription extends SpellDescription {

    @Override
    public String buildDescription(MagicPlayer magicPlayer, boolean advancedInfo) {
        return ChatColor.GRAY + "Lumos will grant you "
                + getScaling("duration", magicPlayer, advancedInfo) + ChatColor.GRAY + " minutes of night vision";
    }

}