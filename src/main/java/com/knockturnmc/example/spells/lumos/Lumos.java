package com.knockturnmc.example.spells.lumos;

import com.knockturnmc.api.game.Tier;
import com.knockturnmc.spellapi.Spellbook;
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
import com.knockturnmc.spellapi.statuseffect.StatusEffect;
import com.knockturnmc.spellapi.utils.bukkit.ParticleUtil;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Pandetta on 5/24/2015.
 */
@Magic(type = SpellType.CHARM, tier = Tier.ONE, time = 40, instant = true, success = .99, power = 3, data = {
        @SpellData(incantation = "LUMOS", motion = "8", inBook = true, description = "The wandlighting spell.")
}, descriptionInstance = LumosDescription.class)
@Scalable({
        @SpellScaling(name = "duration", type = ScalingType.POWER_SCALING, scaling = 1D, cap = 10, transformer = ScalingTransformer.MINUTES)
})
public class Lumos extends Spell {

    public Lumos(SpellInfo info) {
        super(info);
    }

    @Override
    public void effect() {
        Player player = info.getWhoCasted();
        if (player == null) return;

        ParticleUtil.spawnExplodingCircle(player.getLocation().add(0, 1.75 / 2, 0), Particle.FIREWORKS_SPARK, .4, 10, .2);
    }

    @Override
    public void target() {
        info.setState(SpellState.CASTING);
    }

    @Override
    public void cast() {
        Player player = info.getWhoCasted();
        if (player == null) return;

        if (!player.hasPotionEffect(PotionEffectType.BLINDNESS))
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0, false, false));

        player.removePotionEffect(PotionEffectType.NIGHT_VISION);

        int duration = (int) (info.getScaling("duration"));

        StatusEffect build = new LumosEffect(getCasterUUID(), duration, getCasterUUID());
        Spellbook.getInstance().getStatusEffectManager().addEffect(build);
    }

    @Override
    public void backfire() {

    }

    @Override
    public void end() {

    }
}
