package kobting.thepain.cards

import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.actions.common.GainEnergyAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum

class Gulp :
        PainCustomCard(
                ID,
                COST,
                AbstractCard.CardType.SKILL,
                AbstractCardEnum.THE_PAIN_MAROON,
                AbstractCard.CardRarity.UNCOMMON,
                CardTarget.SELF)
{

    companion object {

        @JvmStatic
        val ID = "thepain:Gulp"

        private const val COST = 0
        private const val SELF_DAMAGE = 5
        private const val UPGRADE_SELF_DAMAGE = 3
    }

    init {
        this.baseMagicNumber = SELF_DAMAGE
        this.magicNumber = this.baseMagicNumber
    }

    override fun use(player: AbstractPlayer?, monster: AbstractMonster?) {
        AbstractDungeon.actionManager.addToBottom(DamageAction(player, DamageInfo(player, magicNumber, DamageInfo.DamageType.HP_LOSS)))
        AbstractDungeon.actionManager.addToBottom(GainEnergyAction(if(upgraded) 2 else 1))
    }

    override fun upgrade() {
        if(!upgraded) {
            upgradeName()
            upgradeMagicNumber(UPGRADE_SELF_DAMAGE)
            upgradeDescription()
        }
    }

    override fun makeCopy(): AbstractCard {
        return Gulp()
    }
}