package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import enums.AbstractCardEnum;
import initliziers.ThePainInitializer;

public class Cripple extends CustomCard {

    private static final int COST = 2;
    private static final int WEAKEN_AMT = 2;
    private static final int HEAL_AMT = 3;
    private static final int HEAL_UPG_AMT = 2;

    public static final String ID = "Cripple";
    public static final String NAME = "Crippling Blood";
    public static final String DESCRIPTION = "Apply " + WEAKEN_AMT + " Weakness. NL Heal !M! HP.";


    public Cripple() {
        super(ID, NAME, ThePainInitializer.DEFAULT_CARD_IMAGE_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.THE_PAIN_PURPLE, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        this.magicNumber = this.baseMagicNumber = HEAL_AMT;
    }

    @Override
    public void upgrade() {
        if(!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(HEAL_UPG_AMT);

        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Cripple();
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractMonster, abstractPlayer, new WeakPower(abstractMonster, this.magicNumber, false), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new HealAction(abstractPlayer, abstractPlayer, this.magicNumber));
    }
}
