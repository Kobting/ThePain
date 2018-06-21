package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import enums.AbstractCardEnum;
import initliziers.ThePainInitializer;
import powers.ProtectionPower;

public class DeepCut extends CustomCard {

    private static final int COST = 2;
    private static final int DAMAGE_AMT = 10;
    private static final int UPGRADE_DAMAGE_AMT = 5;

    public static final String ID = "Deep Cut";
    public static final String NAME = "Deep Cut";
    public static final String DESCRIPTION = "Deal !D! Damage. NL Gain 1 Protection";

    public DeepCut() {
        super(ID, NAME, ThePainInitializer.DEFAULT_CARD_IMAGE_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.THE_PAIN_PURPLE, CardRarity.RARE, CardTarget.SELF_AND_ENEMY);
        this.baseDamage = this.damage = DAMAGE_AMT;
    }

    @Override
    public void upgrade() {
        if(!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE_AMT);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new DeepCut();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, DamageInfo.DamageType.NORMAL)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractPlayer, abstractPlayer, new ProtectionPower(abstractPlayer)));
    }
}
