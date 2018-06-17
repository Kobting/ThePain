package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import enums.AbstractCardEnum;
import initliziers.ThePainInitializer;

public class Bandaid extends CustomCard {


    private static final int COST = 1;
    private static final int BLOCK_AMT = 8;
    private static final int HEAL_AMT = 2;
    private static final int BLOCK_UPG_AMT = 2;
    private static final int HEAL_UPG = 1;

    public static final String ID = "Bandaid";
    public static final String NAME = "Bandaid";
    public static final String DESCRIPTION = "Block for !B! and heal for !M!";


    public Bandaid() {
        super(ID, NAME, ThePainInitializer.DEFAULT_CARD_IMAGE_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.THE_PAIN_PURPLE, CardRarity.COMMON, CardTarget.SELF);
        this.block = this.baseBlock = BLOCK_AMT;
        this.magicNumber = this.baseMagicNumber = HEAL_AMT;
    }

    @Override
    public void upgrade() {
        if(!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(BLOCK_UPG_AMT);
            this.upgradeMagicNumber(HEAL_UPG);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Bandaid();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(abstractPlayer, abstractPlayer ,this.block));
        AbstractDungeon.actionManager.addToBottom(new HealAction(abstractPlayer, abstractPlayer, this.magicNumber));

    }

}
