package com.knockturnmc.example.spells.nullum_obscurus;

import com.knockturnmc.spellapi.statuseffect.StatusEffect;
import com.knockturnmc.spellapi.statuseffect.annotation.SingleInstanceEffect;
import com.knockturnmc.spellapi.statuseffect.factory.StatusEffectFactory;
import com.knockturnmc.spellapi.statuseffect.types.uuid.potion.RepeatingPotionEffectBase;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@SingleInstanceEffect
public class NullumObscurusBackfireEffect extends StatusEffect<RepeatingPotionEffectBase<Player>> {

    public NullumObscurusBackfireEffect(StatusEffectFactory factory, int ticks, Player target) {
        super(factory.repeatingPotionEffect(target.getUniqueId(), target, new PotionEffect(PotionEffectType.BLINDNESS, ticks, 2)));
    }

    public NullumObscurusBackfireEffect(RepeatingPotionEffectBase<Player> base) {
        super(base);
    }
}
