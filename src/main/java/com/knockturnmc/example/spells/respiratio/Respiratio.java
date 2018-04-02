package com.knockturnmc.example.spells.respiratio;

import com.knockturnmc.api.game.Tier;
import com.knockturnmc.spellapi.player.MagicPlayer;
import com.knockturnmc.spellapi.spell.Spell;
import com.knockturnmc.spellapi.spell.SpellState;
import com.knockturnmc.spellapi.spell.annotation.Magic;
import com.knockturnmc.spellapi.spell.annotation.SpellData;
import com.knockturnmc.spellapi.spell.annotation.SpellType;
import com.knockturnmc.spellapi.spell.info.SpellInfo;
import com.knockturnmc.spellapi.spell.scaling.Scalable;
import com.knockturnmc.spellapi.spell.scaling.ScalingTransformer;
import com.knockturnmc.spellapi.spell.scaling.ScalingType;
import com.knockturnmc.spellapi.spell.scaling.SpellScaling;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Crane on 03/12/2015.
 */
@Magic(type = SpellType.CHARM, tier = Tier.ONE, power = 5, success = .2, instant = true, data = {
        @SpellData(incantation = "Respiratio", motion = "758")
}, descriptionInstance = RespiratioDescription.class)
@Scalable({
        @SpellScaling(name = "duration", type = ScalingType.POWER_SCALING, scaling = 2, cap = 60, transformer = ScalingTransformer.SECONDS)
})
public class Respiratio extends Spell {

    public Respiratio(SpellInfo info) {
        super(info);
    }

    @Override
    public void target() {
        info.setState(SpellState.CASTING);
    }

    @Override
    public void cast() {
        MagicPlayer magicCaster = info.getMagicCaster();
        if (magicCaster == null) return;

        int duration = (int) (info.getScaling("duration"));
        magicCaster.addStatusEffect(new RespiratioEffect(getCasterUUID(), duration, getCasterUUID()));

        info.kill();
    }

    @Override
    public void effect() {

    }

    @Override
    public void backfire() {
        if (info.getWhoCasted() == null) return;
        info.getWhoCasted().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 3 * 20, 1), true);
    }

    @Override
    public void end() {

    }
}
