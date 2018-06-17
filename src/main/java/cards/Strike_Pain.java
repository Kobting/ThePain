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

public class Strike_Pain extends CustomCard {

    private static final int COST = 1;
    private static final int ATTACK_DMG = 6;
    private static final int UPGRADE_PLUS_DMG = 3;

    public static final String ID = "Strike_PAIN";
    public static final String NAME = "Strike";
    public static final String DESCRIPTION = "Deal !D! damage.";

    public Strike_Pain() {
        super(ID, NAME, ThePainInitializer.ATTACK_CARD_IMAGE_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.THE_PAIN_PURPLE, CardRarity.BASIC, CardTarget.ENEMY);
        this.damage = this.baseDamage = ATTACK_DMG;
    }

    @Override
    public void upgrade() {
        if(!upgraded){
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Strike_Pain();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, DamageInfo.DamageType.NORMAL)));
    }
}
