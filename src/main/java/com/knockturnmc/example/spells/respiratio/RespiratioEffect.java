package com.knockturnmc.example.spells.respiratio;

import com.knockturnmc.spellapi.Spellbook;
import com.knockturnmc.spellapi.spell.info.AnimationHandler;
import com.knockturnmc.spellapi.statuseffect.StatusEffect;
import com.knockturnmc.spellapi.statuseffect.annotation.SingleInstanceEffect;
import com.knockturnmc.spellapi.statuseffect.factory.StatusEffectFactory;
import com.knockturnmc.spellapi.statuseffect.types.uuid.potion.RepeatingPotionEffectBase;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@SingleInstanceEffect
public class RespiratioEffect extends StatusEffect<RepeatingPotionEffectBase<LivingEntity>> {

    private AnimationHandler<LivingEntity> handler;

    public RespiratioEffect(StatusEffectFactory factory, int ticks, LivingEntity target) {
        super(factory.repeatingPotionEffect(target.getUniqueId(), target, new PotionEffect(PotionEffectType.WATER_BREATHING, ticks, 2)));
    }

    public RespiratioEffect(RepeatingPotionEffectBase<LivingEntity> base) {
        super(base);
    }

    @Override
    public void effect() {
        super.effect();

        if (handler == null) {
            handler = Spellbook.getInstance().newAnimationHandler(LivingEntity.class);

            handler.addAnimation(new RespiratioAnimation(Particle.DRIP_WATER, 1, Math.PI / 4, Math.PI / 20, 0, getBase().getTicksLeft()));
            handler.addAnimation(new RespiratioAnimation(Particle.DRIP_WATER, 1, -Math.PI / 4, Math.PI / 20, Math.PI, getBase().getTicksLeft()));
        } else {
            LivingEntity target = getBase().getTarget();
            if (target != null) handler.tickAll(target);
        }
    }
}
