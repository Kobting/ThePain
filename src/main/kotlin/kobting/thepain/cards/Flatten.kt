package kobting.thepain.cards

import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum

class Flatten : PainCustomCard(ID, COST, AbstractCard.CardType.ATTACK, AbstractCardEnum.THE_PAIN_PURPLE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF_AND_ENEMY) {
    init {
        this.damage = ATTACK_DMG
        this.baseDamage = this.damage
        this.magicNumber = GOLD_MULTIPLIER
        this.baseMagicNumber = this.magicNumber
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDamage(UPGRADE_PLUS_DMG)
            upgradeMagicNumber(UPGRADE_GOLD_MULTIPLIER)
        }
    }


    override fun makeCopy(): AbstractCard {
        return Flatten()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        val monsterHealth = abstractMonster!!.currentHealth
        val action = DamageAction(abstractMonster, DamageInfo(abstractPlayer, this.damage, DamageInfo.DamageType.NORMAL))
        AbstractDungeon.actionManager.addToBottom(action)
        //Don't steal more gold than the monster had health left
        val damageDelt = if (monsterHealth > action.amount) action.amount else action.amount - monsterHealth
        AbstractDungeon.player.gainGold(damageDelt * this.magicNumber)
    }

    companion object {

        val ID = "thepain:Flatten"

        private val COST = 1
        private val ATTACK_DMG = 15
        private val UPGRADE_PLUS_DMG = 3
        private val GOLD_MULTIPLIER = 1
        private val UPGRADE_GOLD_MULTIPLIER = 1
    }
}
