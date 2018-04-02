package com.knockturnmc.example.spells.respiratio;

import com.knockturnmc.spellapi.Spellbook;
import com.knockturnmc.spellapi.spell.info.AnimationHandler;
import com.knockturnmc.spellapi.statuseffect.annotation.IdentifiableEffect;
import com.knockturnmc.spellapi.statuseffect.annotation.SingleInstanceEffect;
import com.knockturnmc.spellapi.statuseffect.content.RepeatingPotionEffect;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

@SingleInstanceEffect
@IdentifiableEffect(Respiratio.class)
public class RespiratioEffect extends RepeatingPotionEffect {

    private AnimationHandler<LivingEntity> handler;

    public RespiratioEffect(UUID caster, int duration, UUID target) {
        super(caster, new PotionEffect(PotionEffectType.WATER_BREATHING, duration, 1), target);
    }

    public RespiratioEffect(HashMap<String, Serializable> metaMap) {
        super(metaMap);
    }

    @Override
    public void effect() {
        super.effect();

        if (handler == null) {
            handler = Spellbook.getInstance().newAnimationHandler(LivingEntity.class);

            handler.addAnimation(new RespiratioAnimation(Particle.DRIP_WATER, 1, Math.PI / 4, Math.PI / 20, 0, getTicksLeft()));
            handler.addAnimation(new RespiratioAnimation(Particle.DRIP_WATER, 1, -Math.PI / 4, Math.PI / 20, Math.PI, getTicksLeft()));
        } else {
            LivingEntity target = getTarget();
            if (target != null) handler.tickAll(target);
        }
    }
}
