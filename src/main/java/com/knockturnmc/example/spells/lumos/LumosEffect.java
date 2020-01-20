package com.knockturnmc.example.spells.lumos;

import com.knockturnmc.spellapi.statuseffect.StatusEffect;
import com.knockturnmc.spellapi.statuseffect.annotation.SingleInstanceEffect;
import com.knockturnmc.spellapi.statuseffect.factory.StatusEffectFactory;
import com.knockturnmc.spellapi.statuseffect.types.uuid.potion.RepeatingPotionEffectBase;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@SingleInstanceEffect
public class LumosEffect extends StatusEffect<RepeatingPotionEffectBase> {

    public LumosEffect(StatusEffectFactory factory, int ticks, Player caster) {
        super(factory.repeatingPotionEffect(caster.getUniqueId(), caster, new PotionEffect(PotionEffectType.NIGHT_VISION, ticks, 0)));
    }

    public LumosEffect(RepeatingPotionEffectBase base) {
        super(base);
    }
}
