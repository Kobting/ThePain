package cards;

import actions.DiscardHealAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import enums.AbstractCardEnum;
import initliziers.ThePainInitializer;

public class Lost extends CustomCard {

    private static final int COST = 1;
    private static final int DISCARD_AMT = 3;
    private static final int HEAL_AMT = 1;
    private static final int UPGRADE_HEAL_AMT = 1;

    public static final String ID = "Lost";
    public static final String NAME = "Lost";
    public static final String DESCRIPTION = "Discard up to " + DISCARD_AMT + " Cards. NL Heal !M! HP for every Card Discarded.";

    public Lost() {
        super(ID, NAME, ThePainInitializer.DEFAULT_CARD_IMAGE_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.THE_PAIN_PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = HEAL_AMT;
    }

    @Override
    public void upgrade() {
        if(!upgraded){
            upgradeName();
            upgradeMagicNumber(UPGRADE_HEAL_AMT);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Lost();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        DiscardHealAction healAction = new DiscardHealAction(abstractPlayer, abstractPlayer, DISCARD_AMT, true, this.magicNumber);
        AbstractDungeon.actionManager.addToBottom(healAction);
    }



}
