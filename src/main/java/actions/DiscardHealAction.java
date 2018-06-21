package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class DiscardHealAction extends AbstractGameAction {

    private AbstractPlayer player;
    private boolean anyNumber;
    private int maxAmount;
    private int numberDiscarded = 0;
    private static final float DURATION;
    private int healMultiplier;

    public DiscardHealAction(AbstractCreature target, AbstractCreature source, int maxAmount, boolean anyNumber, int healMultiplier) {
        this.player = (AbstractPlayer) target;
        this.anyNumber = anyNumber;
        this.maxAmount = maxAmount;
        this.actionType = ActionType.DISCARD;
        this.healMultiplier = healMultiplier;
        this.setValues(target, source, maxAmount);
        this.duration = DURATION;
    }

    @Override
    public void update() {
        if (this.duration == DURATION) {
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.isDone = true;
                return;
            }
            if (this.player.hand.size() > 0) {
                AbstractDungeon.handCardSelectScreen.open("Discard and Heal", this.maxAmount, anyNumber, true);
                AbstractDungeon.player.hand.applyPowers();
                this.tickDuration();
                return;
            }
        }


        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            Iterator cardIterator = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();

            while (cardIterator.hasNext()) {
                numberDiscarded++;
                AbstractCard c = (AbstractCard) cardIterator.next();
                this.player.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            tickDuration();
        }

        if(AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            AbstractDungeon.actionManager.addToBottom(new HealAction(player, player, this.healMultiplier * numberDiscarded));
            this.isDone = true;
        }

        tickDuration();
    }

    static {
        DURATION = Settings.ACTION_DUR_XFAST;
    }
}
