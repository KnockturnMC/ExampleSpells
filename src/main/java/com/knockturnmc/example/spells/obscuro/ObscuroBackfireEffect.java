package com.knockturnmc.example.spells.obscuro;

import com.knockturnmc.spellapi.statuseffect.annotation.IdentifiableEffect;
import com.knockturnmc.spellapi.statuseffect.annotation.SingleInstanceEffect;
import com.knockturnmc.spellapi.statuseffect.content.RepeatingPotionEffect;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

@IdentifiableEffect(Obscuro.class)
@SingleInstanceEffect
public class ObscuroBackfireEffect extends RepeatingPotionEffect {

    public ObscuroBackfireEffect(UUID caster, int duration, UUID target) {
        super(caster, new PotionEffect(PotionEffectType.BLINDNESS, duration, 0), target);
    }

    public ObscuroBackfireEffect(HashMap<String, Serializable> metaMap) {
        super(metaMap);
    }
}
