package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import enums.AbstractCardEnum;
import initliziers.ThePainInitializer;

public class Sacrifice extends CustomCard {

    private static final int COST = 1;
    private static final int SELF_DMG = 2;
    private static final int CARD_DRAW_AMT = 2;
    private static final int UPGRADE_CARD_DRAW_AMT = 1;

    public static final String ID = "Sacrifice";
    public static final String NAME = "Sacrifice";
    public static final String DESCRIPTION = "Lose " + SELF_DMG + " HP. NL Draw !M! Cards";

    public Sacrifice() {
        super(ID, NAME, ThePainInitializer.DEFAULT_CARD_IMAGE_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.THE_PAIN_PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = CARD_DRAW_AMT;
    }

    @Override
    public void upgrade() {
        if(!upgraded){
            upgradeName();
            upgradeMagicNumber(UPGRADE_CARD_DRAW_AMT);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Sacrifice();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractPlayer, new DamageInfo(abstractPlayer, SELF_DMG)));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(abstractMonster, this.magicNumber));
    }
}
