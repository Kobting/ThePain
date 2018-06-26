package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import enums.AbstractCardEnum;
import initliziers.ThePainInitializer;

public class Dark extends PainCustomCard {

    public static final String ID = "Dark";

    private static final int COST = -2;
    private static final int LOSE_HP_AMT = 3;
    private static final int UPGRADE_LOSE_HP_AMT = 2;

    public Dark() {
        super(ID, COST, CardType.SKILL, AbstractCardEnum.THE_PAIN_PURPLE, CardRarity.COMMON, CardTarget.NONE);
        this.baseMagicNumber = this.magicNumber = LOSE_HP_AMT;
    }

    @Override
    public void upgrade() {
        if(!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_LOSE_HP_AMT);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Dark();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = "Must be Discarded.";
        return false;
    }

    @Override
    public void triggerOnManualDiscard() {
        AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, this.magicNumber, DamageInfo.DamageType.HP_LOSS));
    }
}
