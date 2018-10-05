package kobting.thepain.actions

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.GameActionManager
import com.megacrit.cardcrawl.actions.common.HealAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

class DiscardHealAction(target: AbstractCreature, source: AbstractCreature, private val maxAmount: Int, private val anyNumber: Boolean, private val healMultiplier: Int) : AbstractGameAction() {

    private val player: AbstractPlayer
    private var numberDiscarded = 0

    init {
        this.player = target as AbstractPlayer
        this.actionType = AbstractGameAction.ActionType.DISCARD
        this.setValues(target, source, maxAmount)
        this.duration = DURATION
    }

    override fun update() {
        if (this.duration >= DURATION) {
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.isDone = true
                return
            }
            if (this.player.hand.size() > 0) {
                AbstractDungeon.handCardSelectScreen.open("Discard and Heal", this.maxAmount, anyNumber, true)
                AbstractDungeon.player.hand.applyPowers()
                this.tickDuration()
                return
            }
        }


        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            val cardIterator = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator()

            while (cardIterator.hasNext()) {
                numberDiscarded++
                val c = cardIterator.next() as AbstractCard
                this.player.hand.moveToDiscardPile(c)
                c.triggerOnManualDiscard()
                GameActionManager.incrementDiscard(false)
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true
            tickDuration()
        }

        if (AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            AbstractDungeon.actionManager.addToBottom(HealAction(player, player, this.healMultiplier * numberDiscarded))
            this.isDone = true
        }

        tickDuration()
    }

    companion object {
        private val DURATION: Float

        init {
            DURATION = Settings.ACTION_DUR_XFAST
        }
    }
}
