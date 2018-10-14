package kobting.thepain.cards

import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum

class Cuts : PainCustomCard(ID, COST, AbstractCard.CardType.ATTACK, AbstractCardEnum.THE_PAIN_MAROON, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF_AND_ENEMY) {
    init {
        this.baseDamage = ATTACK_DMG
        this.damage = this.baseDamage
        this.baseMagicNumber = SELF_DMG
        this.magicNumber = this.baseMagicNumber
    }

    override fun upgrade() {
        if (!this.upgraded) {
            this.upgradeName()
            this.upgradeDamage(UPGRADE_PLUS_DMG)
        }
    }


    override fun makeCopy(): AbstractCard {
        return Cuts()
    }

    override fun use(player: AbstractPlayer?, monster: AbstractMonster?) {

        //Damage monster
        AbstractDungeon.actionManager.addToBottom(DamageAction(monster, DamageInfo(player, this.damage, DamageInfo.DamageType.NORMAL)))

        //Damage self
        AbstractDungeon.actionManager.addToBottom(DamageAction(player, DamageInfo(player, this.magicNumber, DamageInfo.DamageType.HP_LOSS)))


    }

    companion object {

        @JvmStatic
        val ID = "thepain:Cuts"

        private val COST = 0
        private val ATTACK_DMG = 6
        private val SELF_DMG = 1
        private val UPGRADE_PLUS_DMG = 3
    }

}
