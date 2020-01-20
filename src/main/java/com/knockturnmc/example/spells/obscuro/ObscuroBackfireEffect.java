package com.knockturnmc.example.spells.obscuro;

import com.knockturnmc.spellapi.statuseffect.StatusEffect;
import com.knockturnmc.spellapi.statuseffect.annotation.SingleInstanceEffect;
import com.knockturnmc.spellapi.statuseffect.factory.StatusEffectFactory;
import com.knockturnmc.spellapi.statuseffect.types.uuid.potion.RepeatingPotionEffectBase;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@SingleInstanceEffect
public class ObscuroBackfireEffect extends StatusEffect<RepeatingPotionEffectBase<Player>> {

    public ObscuroBackfireEffect(StatusEffectFactory factory, int ticks, Player caster) {
        super(factory.repeatingPotionEffect(caster.getUniqueId(), caster, new PotionEffect(PotionEffectType.BLINDNESS, ticks, 0)));
    }

    public ObscuroBackfireEffect(RepeatingPotionEffectBase<Player> base) {
        super(base);
    }
}
