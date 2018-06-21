package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import enums.AbstractCardEnum;
import initliziers.ThePainInitializer;

public class LegBreak extends CustomCard {

    private static final int COST = 2;
    private static final int WEAK_AMT = 3;
    private static final int UPGRADE_WEAK_AMT = 2;

    public static final String ID = "Leg Break";
    public static final String NAME = "Leg Break";
    public static final String DESCRIPTION = "Apply !M! Weak to all enemies";

    public LegBreak() {
        super(ID, NAME, ThePainInitializer.DEFAULT_CARD_IMAGE_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.THE_PAIN_PURPLE, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = this.magicNumber = WEAK_AMT;
    }

    @Override
    public void upgrade() {
        if(!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_WEAK_AMT);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new LegBreak();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        for(AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, abstractMonster, new WeakPower(monster, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }
}
