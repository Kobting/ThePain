package kobting.thepain.cards

import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum
import kobting.thepain.powers.Blood

class DoubleTrouble :
        PainCustomCard(
                ID,
                COST,
                AbstractCard.CardType.SKILL,
                AbstractCardEnum.THE_PAIN_PURPLE,
                AbstractCard.CardRarity.RARE,
                AbstractCard.CardTarget.SELF)
{

    companion object {

        @JvmStatic
        val ID = "thepain:DoubleTrouble"

        private const val COST = 3
        private const val DOUBLE_AMT = 2
    }

    init {
        this.baseMagicNumber = DOUBLE_AMT
        this.magicNumber = this.baseMagicNumber
    }
    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        if(abstractPlayer!!.hasPower(Blood.id)) {
            abstractPlayer.getPower(Blood.id).amount *= 2
            abstractPlayer.getPower(Blood.id).flash()
        }
    }

    override fun upgrade() {
        if(!this.upgraded){
            this.upgradeName()
            this.upgradeBaseCost(2)
        }
    }


}