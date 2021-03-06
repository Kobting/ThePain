package kobting.thepain.cards

import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum
import kobting.thepain.patches.JCharacterPatches

class BloodySkewer :
        PainCustomCard(
                ID,
                COST,
                AbstractCard.CardType.ATTACK,
                AbstractCardEnum.THE_PAIN_MAROON,
                AbstractCard.CardRarity.UNCOMMON,
                AbstractCard.CardTarget.ENEMY)
{
    companion object {

        @JvmStatic
        val ID = "thepain:BloodySkewer"

        private const val COST = 2
        private const val DMG_AMT = 3
        private const val UPGRADE_DMG_AMT = 2
    }

    init {
        this.baseDamage = DMG_AMT
        this.damage = this.baseDamage
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDamage(UPGRADE_DMG_AMT)
        }
    }


    override fun makeCopy(): AbstractCard {
        return BloodySkewer()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {

        var lostHP = JCharacterPatches.bloodBottle_f.get(abstractPlayer).bloodCount

        for (i in 0 until lostHP) {
            AbstractDungeon.actionManager.addToBottom(DamageAction(abstractMonster, DamageInfo(abstractPlayer, this.damage, DamageInfo.DamageType.NORMAL)))
        }
    }


}
