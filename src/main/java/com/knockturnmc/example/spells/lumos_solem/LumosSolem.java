package com.knockturnmc.example.spells.lumos_solem;

import com.knockturnmc.api.game.Tier;
import com.knockturnmc.spellapi.Spellbook;
import com.knockturnmc.spellapi.spell.Spell;
import com.knockturnmc.spellapi.spell.SpellState;
import com.knockturnmc.spellapi.spell.annotation.Magic;
import com.knockturnmc.spellapi.spell.annotation.SpellData;
import com.knockturnmc.spellapi.spell.annotation.SpellType;
import com.knockturnmc.spellapi.spell.info.SpellInfo;
import com.knockturnmc.spellapi.spell.info.targets.TargetType;
import com.knockturnmc.spellapi.spell.scaling.Scalable;
import com.knockturnmc.spellapi.spell.scaling.ScalingTransformer;
import com.knockturnmc.spellapi.spell.scaling.ScalingType;
import com.knockturnmc.spellapi.spell.scaling.SpellScaling;
import com.knockturnmc.spellapi.utils.bukkit.ParticleUtil;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Crane on 03/12/2015.
 */
@Magic(type = SpellType.CHARM, tier = Tier.SIX, pvp = true, success = .15, data = {
        @SpellData(incantation = "LUMOS_SOLEM", motion = "867")
}, descriptionInstance = LumosSolemDescription.class)
@Scalable({
        @SpellScaling(name = "radius", type = ScalingType.POWER_SCALING, scaling = 1, cap = 20),
        @SpellScaling(name = "time_on_fire", type = ScalingType.CONSTANT, base = 5, transformer = ScalingTransformer.SECONDS),
        @SpellScaling(name = "duration", type = ScalingType.POWER_SCALING, base = 5, scaling = 1, cap = 45, transformer = ScalingTransformer.SECONDS)
})
public class LumosSolem extends Spell {

    private static final int CAST_CALLED = 10;

    private double radius = 0;
    private double radiusGrowth = 0;
    private double maxRadius = 0;

    public LumosSolem(SpellInfo info) {
        super(info);
    }

    @Override
    public void target() {
        info.getMove().move();

        List<LivingEntity> targets = info.getLivingEntitiesHit(1);
        if (targets.isEmpty() && info.getSolidBlock() == null) return;

        info.setState(SpellState.CASTING);
        info.setTargetType(TargetType.LIVING_ENTITIES);

        maxRadius = info.getScaling("radius");
        radius = maxRadius / 4;
        radiusGrowth = (maxRadius - radius) / CAST_CALLED;

        info.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, info.getLocation(), 0);
    }

    @Override
    public void castInit() {
        Spellbook.addEffect(new com.knockturnmc.spells.charms.spellsV5.lumos_solem.LumosSolemEffect(getCasterUUID(), (int) info.getScaling("duration"), info.getLocation(), radius));
    }

    @Override
    public void cast() {
        ParticleUtil.spawnExplodingCircle(info.getLocation(), info.lifeticks() % 2 == 0 ? Particle.FIREWORKS_SPARK : Particle.FLAME, 1, (int)
                (radius / 1.5), (radius += radiusGrowth) / 10);

        info.getLocation().getWorld().playSound(info.getLocation(), Sound.BLOCK_FIRE_AMBIENT, 2, 2);

        info.getTargetFinder().getEntitiesInRadius(LivingEntity.class, info.getLocation(), radius).stream()
                .filter(livingEntity -> !info.getTargetEntities().contains(livingEntity.getUniqueId())) //Not in list
                .filter(livingEntity -> livingEntity != info.getWhoCasted()) //Not the caster
                .forEach(livingEntity -> {
                    info.addTargetEntity(livingEntity.getUniqueId());

                    livingEntity.setFireTicks((int) (info.getScaling("time_on_fire")));
                });

        if (radius >= maxRadius) info.kill();
    }

    @Override
    public void effect() {
        info.getMove().setColor(Color.YELLOW);
    }

    @Override
    public void backfire() {
        Player caster = getCaster();
        if (caster != null) {
            caster.setFireTicks(20 * 20);
        }
    }

    @Override
    public void end() {

    }
}
