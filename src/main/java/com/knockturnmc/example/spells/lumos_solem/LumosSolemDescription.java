package com.knockturnmc.example.spells.lumos_solem;

import com.knockturnmc.spellapi.player.MagicPlayer;
import com.knockturnmc.spellapi.spell.SpellDescription;
import org.bukkit.ChatColor;

public class LumosSolemDescription extends SpellDescription {

    @Override
    public String buildDescription(MagicPlayer magicPlayer, boolean b) {
        return ChatColor.GRAY + "Lumos Solem will ignite enemies in a "
                + getScaling("radius" , magicPlayer , b) + ChatColor.GRAY + " block radius for "
                + getScaling("time_on_fire" , magicPlayer , b) + ChatColor.GRAY + " seconds";
    }
}
