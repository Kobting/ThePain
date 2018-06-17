package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import enums.AbstractCardEnum;
import initliziers.ThePainInitializer;

public class DropOfBlood extends CustomCard {

    private static final int COST = 2;
    private static final int HEAL_AMT = 15;
    private static final int UPGRADE_HEAL_AMT = 5;

    public static final String ID = "Drop_Of_Blood";
    public static final String NAME = "Drop of Blood";
    public static final String DESCRIPTION = "Heal !M! HP. NL Ethereal. NL Exhaust.";

    public DropOfBlood() {
        super(ID, NAME, ThePainInitializer.DEFAULT_CARD_IMAGE_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.THE_PAIN_PURPLE, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = HEAL_AMT;
        this.exhaust = true;
        this.isEthereal = true;
    }

    @Override
    public void upgrade() {
        if(!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_HEAL_AMT);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new DropOfBlood();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new HealAction(abstractPlayer, abstractPlayer, this.magicNumber));
    }
}
