package com.knockturnmc.example.spells.flarespell;

import com.knockturnmc.spellapi.player.MagicPlayer;
import com.knockturnmc.spellapi.spell.SpellDescription;
import org.bukkit.ChatColor;

public class FlareSpellDescription extends SpellDescription {

    @Override
    public String buildDescription(MagicPlayer magicPlayer, boolean b) {
        return ChatColor.GRAY + getSourceClass().getFormattedIncantation() + " will fly " + getScaling("length", magicPlayer, b) + ChatColor.GRAY + " blocks";
    }

}
