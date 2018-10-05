package kobting.thepain.relics

import basemod.abstracts.CustomRelic
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.relics.AbstractRelic
import kobting.thepain.ThePainInitializer
import kobting.thepain.powers.BloodPower


class ShatteredGlass : CustomRelic(ID, ThePainInitializer.starterRelicTexture, ThePainInitializer.starterRelicTexture, AbstractRelic.RelicTier.STARTER, AbstractRelic.LandingSound.MAGICAL) {

    override fun makeCopy(): AbstractRelic {
        return ShatteredGlass()
    }

    override fun getUpdatedDescription(): String {
        return DESCRIPTIONS[0]
    }


    override fun onPlayerEndTurn() {
        super.onPlayerEndTurn()
        if (AbstractDungeon.player.hasPower(BloodPower.id)) {
            val bloodCount = AbstractDungeon.player.getPower(BloodPower.id).amount
            if (bloodCount > 0) {
                for (monster in AbstractDungeon.getCurrRoom().monsters.monsters) {
                    monster.damage(DamageInfo(AbstractDungeon.player, bloodCount * 2, DamageInfo.DamageType.THORNS))
                }
            }
        } else {
            //If somehow the player doesn't have blood give it to them.
            val player = AbstractDungeon.player
            AbstractDungeon.actionManager.addToBottom(ApplyPowerAction(player, player, BloodPower(player)))
        }


    }

    companion object {

        @JvmStatic
        val ID = "Shattered_Glass"
    }

}
