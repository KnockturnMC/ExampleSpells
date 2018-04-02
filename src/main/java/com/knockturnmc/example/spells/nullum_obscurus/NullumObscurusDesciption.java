package com.knockturnmc.example.spells.nullum_obscurus;

import com.knockturnmc.spellapi.player.MagicPlayer;
import com.knockturnmc.spellapi.spell.SpellDescription;
import org.bukkit.ChatColor;

public class NullumObscurusDesciption extends SpellDescription {

    @Override
    public String buildDescription(MagicPlayer magicPlayer, boolean b) {
        return ChatColor.GRAY + "Nullum Obscurus will reduce the time of an Obscuro-Effect by "
                + getScaling("duration", magicPlayer, b) + ChatColor.GRAY + " minutes";
    }
}
