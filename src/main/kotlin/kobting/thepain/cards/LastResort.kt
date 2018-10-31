package kobting.thepain.cards

import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum

class LastResort :
        PainCustomCard(
                ID,
                COST,
                AbstractCard.CardType.SKILL,
                AbstractCardEnum.THE_PAIN_MAROON,
                AbstractCard.CardRarity.RARE,
                AbstractCard.CardTarget.SELF
        ){

    companion object {
        val ID = "thepain:LastResort"

        private const val COST = 1
    }

    override fun use(player: AbstractPlayer?, monster: AbstractMonster?) {
        var hpLose = player!!.currentHealth - 1
        AbstractDungeon.actionManager.addToBottom(DamageAction(player, DamageInfo(player, hpLose, DamageInfo.DamageType.HP_LOSS)))
    }

    override fun upgrade() {
        if(!upgraded){
            upgradeName()
            upgradeBaseCost(0)
        }
    }


}