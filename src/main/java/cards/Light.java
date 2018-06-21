package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import enums.AbstractCardEnum;
import initliziers.ThePainInitializer;

public class Light extends CustomCard {

    private static final int COST = -2;
    private static final int HEAL_AMT = 5;
    private static final int UPGRADE_HEAL_AMT = 2;

    public static final String ID = "Light";
    public static final String NAME = "Light";
    public static final String DESCRIPTION = "Unplayable. NL Heal !M! HP when Discarded.";

    public Light() {
        super(ID, NAME, ThePainInitializer.DEFAULT_CARD_IMAGE_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.THE_PAIN_PURPLE, CardRarity.UNCOMMON, CardTarget.NONE);
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
        return new Light();
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = "Must be Discarded.";
        return false;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

    @Override
    public void triggerOnManualDiscard() {
        AbstractDungeon.player.heal(this.magicNumber);
    }
}
