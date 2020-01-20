package com.knockturnmc.example.spells.immobulus;

import com.knockturnmc.api.game.Tier;
import com.knockturnmc.spellapi.Spellbook;
import com.knockturnmc.spellapi.spell.Spell;
import com.knockturnmc.spellapi.spell.SpellState;
import com.knockturnmc.spellapi.spell.annotation.Magic;
import com.knockturnmc.spellapi.spell.annotation.SpellData;
import com.knockturnmc.spellapi.spell.annotation.SpellType;
import com.knockturnmc.spellapi.spell.info.SpellInfo;
import com.knockturnmc.spellapi.spell.info.targets.TargetType;
import com.knockturnmc.spellapi.spell.info.targets.UnlinkedTargetfinder;
import com.knockturnmc.spellapi.spell.scaling.Scalable;
import com.knockturnmc.spellapi.spell.scaling.ScalingTransformer;
import com.knockturnmc.spellapi.spell.scaling.ScalingType;
import com.knockturnmc.spellapi.spell.scaling.SpellScaling;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * Created by kboynton on 5/31/2016.
 */
@Magic(type = SpellType.HEX, tier = Tier.EIGHT, time = 20, success = .15, backfire = .60, pvp = true, coolDown = 20, data = {
        @SpellData(incantation = "IMMOBULUS", motion = "1899")
}, descriptionInstance = ImmobulusDescription.class)
@Scalable({
        @SpellScaling(name = "radius", type = ScalingType.POWER_SCALING, scaling = .5, cap = 8),
        @SpellScaling(name = "duration", type = ScalingType.POWER_SCALING, scaling = 1.5, cap = 30, transformer = ScalingTransformer.SECONDS)
})
public class Immobulus extends Spell {

    public Immobulus(SpellInfo info) {
        super(info);
    }

    @Override
    public void target() {
        info.getMove().move();

        Location loc = info.getNonSolidBlock();
        if (loc == null) return;

        info.setState(SpellState.CASTING);
        info.setTargetType(TargetType.LOCATION);
        info.setTargetLocation(loc);
    }

    @Override
    public void cast() {
        UnlinkedTargetfinder unlinkedCopy = info.getTargetFinder().createUnlinkedCopy();
        unlinkedCopy.setRadius((float) info.getScaling("radius"));

        int duration = (int) info.getScaling("duration");

        Spellbook.addEffect(new ImmobulusEffect(info.getEffectFactory(), duration, getCasterUUID(), info.getTargetLocation(), unlinkedCopy));
        info.kill();
    }

    @Override
    public void effect() {
        info.getMove().setColor(Color.GRAY);
    }

    @Override
    public void backfire() {
        info.getWhoCasted().setVelocity(new Vector(0, 0, 0));
    }

    @Override
    public void end() {

    }
}
