package com.knockturnmc.example.spells.flarespell;

import com.knockturnmc.api.game.Tier;
import com.knockturnmc.spellapi.spell.Spell;
import com.knockturnmc.spellapi.spell.annotation.Magic;
import com.knockturnmc.spellapi.spell.annotation.SpellData;
import com.knockturnmc.spellapi.spell.annotation.SpellType;
import com.knockturnmc.spellapi.spell.info.SpellInfo;
import com.knockturnmc.spellapi.spell.scaling.Scalable;
import com.knockturnmc.spellapi.spell.scaling.ScalingType;
import com.knockturnmc.spellapi.spell.scaling.SpellScaling;
import org.bukkit.Location;
import org.bukkit.util.Vector;


/**
 * Created by kboynton on 11/14/2016.
 */
@Magic(type = SpellType.CHARM, tier = Tier.ONE, time = 1, success = .8, backfire = 0, coolDown = 3,
        data = {
                @SpellData(incantation = "PERICULUM", motion = "9", inBook = true),
                @SpellData(incantation = "VERDIMILLIOUS", motion = "91", inBook = true),
                @SpellData(incantation = "VERMILLIOUS", motion = "99", inBook = true),
                @SpellData(incantation = "ACORIUM", motion = "96", inBook = true),
                @SpellData(incantation = "AERILLIOUS", motion = "93", inBook = true),
                @SpellData(incantation = "BLORIUM", motion = "97"),
                @SpellData(incantation = "NORIUM", motion = "94"),
                @SpellData(incantation = "VIOLUM", motion = "92")
        }, descriptionInstance = FlareSpellDescription.class)
@Scalable({
        @SpellScaling(name = "length", type = ScalingType.POWER_SCALING, scaling = .8D, cap = 20D),
        @SpellScaling(name = "particles_per_block", type = ScalingType.CONSTANT, base = 10)
})
public class FlareSpell extends Spell {

    public FlareSpell(SpellInfo info) {
        super(info);
    }

    @Override
    public void target() {

    }

    @Override
    public void cast() {

    }

    @Override
    public void effect() {
        Flare color = Flare.valueOf(info.getViableSpell().getUpperCaseIncantation());
        getMove().setColor(color.getColor());

        double length = info.getScaling("length");
        double oneMove = 1D / info.getScaling("particles_per_block");

        Vector vector = info.getVector().clone().normalize().multiply(oneMove);
        Location start = info.getLocation();

        for (double i = 0; i < length; i += oneMove) {
            info.getMove().getParticleObject().display(start);
            start.add(vector);
        }

        info.kill();
    }

    @Override
    public void backfire() {

    }

    @Override
    public void end() {

    }

}
