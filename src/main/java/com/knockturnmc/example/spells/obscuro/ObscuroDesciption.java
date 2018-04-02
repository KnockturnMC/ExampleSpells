package com.knockturnmc.example.spells.obscuro;

import com.knockturnmc.spellapi.player.MagicPlayer;
import com.knockturnmc.spellapi.spell.SpellDescription;
import org.bukkit.ChatColor;

public class ObscuroDesciption extends SpellDescription {

    @Override
    public String buildDescription(MagicPlayer magicPlayer, boolean b) {
        return ChatColor.GRAY + "Obscuro will blind the first player it hits for "
                + getScaling("duration" , magicPlayer , b) + ChatColor.GRAY + " minutes";
    }
}
