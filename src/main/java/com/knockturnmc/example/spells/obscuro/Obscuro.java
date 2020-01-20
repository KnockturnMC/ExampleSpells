package com.knockturnmc.example.spells.obscuro;

import com.knockturnmc.api.game.Tier;
import com.knockturnmc.spellapi.Spellbook;
import com.knockturnmc.spellapi.spell.Spell;
import com.knockturnmc.spellapi.spell.SpellState;
import com.knockturnmc.spellapi.spell.annotation.Magic;
import com.knockturnmc.spellapi.spell.annotation.SpellData;
import com.knockturnmc.spellapi.spell.annotation.SpellType;
import com.knockturnmc.spellapi.spell.info.SpellInfo;
import com.knockturnmc.spellapi.spell.info.movement.BounceType;
import com.knockturnmc.spellapi.spell.info.targets.TargetType;
import com.knockturnmc.spellapi.spell.scaling.Scalable;
import com.knockturnmc.spellapi.spell.scaling.ScalingTransformer;
import com.knockturnmc.spellapi.spell.scaling.ScalingType;
import com.knockturnmc.spellapi.spell.scaling.SpellScaling;
import org.bukkit.Color;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

/**
 * Created by Pandetta on 7/2/2015.
 */
@Magic(type = SpellType.HEX, tier = Tier.TWO, time = 10 * 20, power = 2, success = .45, backfire = .50, pvp = true, data = {
        @SpellData(incantation = "OBSCURO", motion = "78", inBook = true)
}, descriptionInstance = ObscuroDesciption.class)
@Scalable({
        @SpellScaling(name = "duration", type = ScalingType.POWER_SCALING, scaling = .3, cap = 3, transformer = ScalingTransformer.MINUTES)
})
public class Obscuro extends Spell {

    public Obscuro(SpellInfo info) {
        super(info);
    }

    @Override
    public void target() {
        info.getMove().move();

        List<Player> list = info.getPlayersHit(1);
        if (list.isEmpty()) return;

        list.stream().map(Player::getUniqueId).forEach(info::addTargetEntity);

        info.setTargetType(TargetType.PLAYERS);
        info.setState(SpellState.CASTING);
    }

    @Override
    public void cast() {
        int duration = (int) (info.getScaling("duration"));

        info.getTargetEntities().stream()
                .map(Spellbook.getInstance().getMagicPlayerHandler()::find)
                .filter(Objects::nonNull)
                .forEach(player -> player.getEffectContainer().add(new ObscuroEffect(info.getEffectFactory(), duration, getCasterUUID(), player.getPlayer())));

        info.kill();
    }

    @Override
    public void effect() {
        info.getMove().setBouncing(BounceType.BOUNCE);
        info.getMove().setColor(Color.BLACK);
    }

    @Override
    public void backfire() {
        Player caster = getCaster();
        if (caster == null) return;

        Spellbook.addEffect(new ObscuroBackfireEffect(info.getEffectFactory(), 30 * 20, caster));
    }

    @Override
    public void end() {

    }
}
