package kobting.thepain.relics


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.relics.AbstractRelic
import kobting.thepain.powers.Blood

class BloodBag : PainCustomRelic(ID, AbstractRelic.RelicTier.UNCOMMON, AbstractRelic.LandingSound.MAGICAL) {

    companion object {

        @JvmStatic
        val ID = "thepain:BloodBag"
    }

    override fun getUpdatedDescription(): String {
        return DESCRIPTIONS[0]
    }

    override fun atBattleStart() {
        if(AbstractDungeon.player.hasPower(Blood.id)){
            AbstractDungeon.player.getPower(Blood.id).stackPower(3)
        } else {
            val player = AbstractDungeon.player
            val power = Blood(AbstractDungeon.player)
            power.amount += 3
            AbstractDungeon.actionManager.addToBottom(ApplyPowerAction(player,player,power))
        }
    }
}