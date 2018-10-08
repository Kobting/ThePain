package kobting.thepain.relics

import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.relics.AbstractRelic
import kobting.thepain.patches.JCharacterPatches


class ShatteredGlass : PainCustomRelic(ID, AbstractRelic.RelicTier.STARTER, AbstractRelic.LandingSound.MAGICAL) {

    override fun makeCopy(): AbstractRelic {
        return ShatteredGlass()
    }

    override fun getUpdatedDescription(): String {
        return DESCRIPTIONS[0]
    }


    override fun onPlayerEndTurn() {
        super.onPlayerEndTurn()
        val bloodCount = JCharacterPatches.bloodBottle_f.get(AbstractDungeon.player).bloodCount
        println("ThePain: ShatteredGlass blood count $bloodCount")
        if (bloodCount > 0) {
            for (monster in AbstractDungeon.getCurrRoom().monsters.monsters) {
                monster.damage(DamageInfo(AbstractDungeon.player, bloodCount * 2, DamageInfo.DamageType.THORNS))
            }
        }

    }

    companion object {

        @JvmStatic
        val ID = "thepain:ShatteredGlass"
    }

}
