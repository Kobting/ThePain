package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import enums.AbstractCardEnum;
import initliziers.ThePainInitializer;

public class CutDry extends CustomCard {

    private static final int COST = 1;
    private static final int SELF_DMG = 2;
    private static final int HEAL_AMT = 3;

    public static final String ID = "Cut_Dry";
    public static final String NAME = "Cut and Dry";
    public static final String DESCRIPTION = "Lose " + SELF_DMG + " HP. NL Heal !M! HP.";

    public CutDry() {
        super(ID, NAME, ThePainInitializer.DEFAULT_CARD_IMAGE_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.THE_PAIN_PURPLE, CardRarity.BASIC, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = HEAL_AMT;
    }

    @Override
    public void upgrade() {
        if(!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CutDry();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        //Not using default damage variable to prevent weakness and strength from effecting the self damage.
        AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractPlayer, new DamageInfo(abstractPlayer, SELF_DMG, DamageInfo.DamageType.HP_LOSS)));

        AbstractDungeon.actionManager.addToBottom(new HealAction(abstractPlayer, abstractPlayer, this.magicNumber));
    }
}
