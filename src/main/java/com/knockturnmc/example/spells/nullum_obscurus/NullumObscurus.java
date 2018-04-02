package com.knockturnmc.example.spells.nullum_obscurus;

import com.knockturnmc.api.game.Tier;
import com.knockturnmc.example.spells.obscuro.ObscuroEffect;
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
@Magic(type = SpellType.COUNTERSPELL, tier = Tier.THREE, time = 20 * 7, power = 2, success = .4, backfire = .5, data = {
        @SpellData(incantation = "NULLUM_OBSCURUS", motion = "87")
}, descriptionInstance = NullumObscurusDesciption.class)
@Scalable({
        @SpellScaling(name = "duration", type = ScalingType.POWER_SCALING, scaling = .2, cap = 3, transformer = ScalingTransformer.MINUTES)
})
public class NullumObscurus extends Spell {

    public NullumObscurus(SpellInfo info) {
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

        List<Player> players = info.getTargetEntities(Player.class);

        players.stream()
                .map(Player::getUniqueId)
                .map(Spellbook.getInstance().getMagicPlayerHandler()::find)
                .filter(Objects::nonNull)
                .forEach(player -> {
                    player.getEffects(ObscuroEffect.class).forEach(effect -> effect.setTicks(effect.getTicks() + duration));
                });

        info.kill();
    }

    @Override
    public void effect() {
        info.getMove().setBouncing(BounceType.BOUNCE);
        info.getMove().setColor(Color.WHITE);
    }

    @Override
    public void backfire() {
        Spellbook.addEffect(new NullumObscurusBackfireEffect(getCasterUUID(), 20 * 20, getCasterUUID()));
    }

    @Override
    public void end() {

    }
}
