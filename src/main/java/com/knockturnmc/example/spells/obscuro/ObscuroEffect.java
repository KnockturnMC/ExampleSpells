package com.knockturnmc.example.spells.obscuro;

import com.knockturnmc.spellapi.statuseffect.StatusEffect;
import com.knockturnmc.spellapi.statuseffect.annotation.SingleInstanceEffect;
import com.knockturnmc.spellapi.statuseffect.factory.StatusEffectFactory;
import com.knockturnmc.spellapi.statuseffect.types.uuid.potion.RepeatingPotionEffectBase;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

@SingleInstanceEffect
public class ObscuroEffect extends StatusEffect<RepeatingPotionEffectBase<Player>> {

    public ObscuroEffect(StatusEffectFactory factory, int ticks, UUID caster, Player target) {
        super(factory.repeatingPotionEffect(caster, target, new PotionEffect(PotionEffectType.BLINDNESS, ticks, 0)));
    }

    public ObscuroEffect(RepeatingPotionEffectBase<Player> base) {
        super(base);
    }
}
