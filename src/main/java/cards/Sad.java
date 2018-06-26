package cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import enums.AbstractCardEnum;

public class Sad extends PainCustomCard {

    public static final String ID = "Sad";

    private static final int COST = 0;
    private static final int SELF_DAMAGE = 3;
    private static final int UPGRADE_SELF_DAMAGE = 2;

    public Sad() {
        super(ID, COST, CardType.SKILL, AbstractCardEnum.THE_PAIN_PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = SELF_DAMAGE;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if(!upgraded) {
            upgradeName();
            upgradeDescription();
            upgradeMagicNumber(UPGRADE_SELF_DAMAGE);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Sad();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractPlayer, new DamageInfo(abstractPlayer, this.magicNumber, DamageInfo.DamageType.HP_LOSS)));
        Happy happy = new Happy();
        if(upgraded) happy.upgrade();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(happy, 1));
    }
}
