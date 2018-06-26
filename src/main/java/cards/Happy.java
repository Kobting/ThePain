package cards;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import enums.AbstractCardEnum;

public class Happy extends PainCustomCard {

    public static final String ID = "Happy";

    private static final int COST = 1;
    private static final int HEAL_AMT = 3;
    private static final int UPGRADE_HEAL_AMT = 2;

    public Happy() {
        super(ID, COST, CardType.SKILL, AbstractCardEnum.THE_PAIN_PURPLE, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = HEAL_AMT;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if(!upgraded) {
            upgradeName();
            upgradeDescription();
            upgradeMagicNumber(UPGRADE_HEAL_AMT);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Happy();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new HealAction(abstractPlayer, abstractPlayer, this.magicNumber));
        Sad sad = new Sad();
        if(upgraded) sad.upgrade();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(sad, 1));
    }

}
