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

public class Flatten extends CustomCard {

    private static final int COST = 1;
    private static final int ATTACK_DMG = 15;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int GOLD_MULTIPLIER = 1;
    private static final int UPGRADE_GOLD_MULTIPLIER = 1;

    public static final String ID = "Flatten";
    public static final String NAME = "Flatten";
    public static final String DESCRIPTION = "Deal !D! damage. NL Gain !M!X gold of Damage given.";

    public Flatten() {
        super(ID, NAME, ThePainInitializer.DEFAULT_CARD_IMAGE_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.THE_PAIN_PURPLE, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        this.baseDamage = this.damage = ATTACK_DMG;
        this.baseMagicNumber = this.magicNumber = GOLD_MULTIPLIER;
    }

    @Override
    public void upgrade() {
        if(!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_GOLD_MULTIPLIER);
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new Flatten();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        int monsterHealth = abstractMonster.currentHealth;
        DamageAction action = new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, DamageInfo.DamageType.NORMAL));
        AbstractDungeon.actionManager.addToBottom(action);
        //Don't steal more gold than the monster had health left
        int damageDelt = monsterHealth > action.amount ? action.amount : action.amount - monsterHealth;
        AbstractDungeon.player.gainGold(damageDelt * this.magicNumber);
    }
}
