package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import enums.AbstractCardEnum;
import initliziers.ThePainInitializer;

public class Bleed extends CustomCard {

    private static final int COST = 0;
    private static final int SELF_DMG = 5;
    private static final int UPGRADE_PLUS_DMG = 3;

    public static final String ID = "Bleed";
    public static final String NAME = "Bleed";
    public static final String DESCRIPTION = "Take !M! damage.";

    public Bleed() {
        super(ID, NAME, ThePainInitializer.BLEED_CARD_IMAGE_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.THE_PAIN_PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = SELF_DMG;
    }

    @Override
    public void upgrade() {
        if(!upgraded){
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DMG);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Bleed();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractPlayer, new DamageInfo(abstractPlayer, this.magicNumber)));
    }
}
