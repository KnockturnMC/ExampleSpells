package com.knockturnmc.example.spells.flarespell;

import com.knockturnmc.spellapi.player.MagicPlayer;
import com.knockturnmc.spellapi.spell.SpellDescription;
import org.bukkit.ChatColor;

public class FlareSpellDescription extends SpellDescription {

    @Override
    public String buildDescription(MagicPlayer magicPlayer, boolean b) {
        Flare flare = Flare.valueOf(getSourceClass().getUpperCaseIncantation());

        return ChatColor.GRAY + getSourceClass().getFormattedIncantation() + " will spawn a " + flare.getColorString() +
                " flare for " + getScaling("length", magicPlayer, b) + ChatColor.GRAY + " blocks";
    }

}
