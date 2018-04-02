package com.knockturnmc.example.spells.lumos;

import com.knockturnmc.spellapi.statuseffect.annotation.IdentifiableEffect;
import com.knockturnmc.spellapi.statuseffect.annotation.SingleInstanceEffect;
import com.knockturnmc.spellapi.statuseffect.content.RepeatingPotionEffect;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

@SingleInstanceEffect
@IdentifiableEffect(value = Lumos.class)
public class LumosEffect extends RepeatingPotionEffect {

    public LumosEffect(UUID caster, int duration, UUID target) {
        super(caster, new PotionEffect(PotionEffectType.NIGHT_VISION, duration, 0, false, false), target);
    }

    public LumosEffect(HashMap<String, Serializable> metaMap) {
        super(metaMap);
    }
}
