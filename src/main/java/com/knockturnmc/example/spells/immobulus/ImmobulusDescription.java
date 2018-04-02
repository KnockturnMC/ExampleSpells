package com.knockturnmc.example.spells.immobulus;

import com.knockturnmc.spellapi.player.MagicPlayer;
import com.knockturnmc.spellapi.spell.SpellDescription;
import org.bukkit.ChatColor;

public class ImmobulusDescription extends SpellDescription {

    @Override
    public String buildDescription(MagicPlayer magicPlayer, boolean b) {
        return ChatColor.GRAY + "Immobulus will prevent movement in a "
                + getScaling("radius" , magicPlayer , b) + ChatColor.GRAY + " blocks radius for "
                + getScaling("duration" , magicPlayer ,b) + ChatColor.GRAY + " seconds";
    }
}
