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

public class Cuts extends CustomCard {


    private static final int COST = 0;
    private static final int ATTACK_DMG = 6;
    private static final int SELF_DMG = 1;
    private static final int UPGRADE_PLUS_DMG = 3;

    public static final String ID = "Cuts";
    public static final String NAME = "Cuts";
    public static final String DESCRIPTION = "Deal !D! damage. NL Take !M! damage.";

    public Cuts() {
        super(ID, NAME, ThePainInitializer.DEFAULT_CARD_IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCardEnum.THE_PAIN_PURPLE, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        this.damage = this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = SELF_DMG;
    }

    @Override
    public void upgrade() {
        if(!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }



    @Override
    public AbstractCard makeCopy() {
        return new Cuts();
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster) {

        //Damage monster
        AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(player, this.damage, DamageInfo.DamageType.NORMAL)));

        //Damage self
        AbstractDungeon.actionManager.addToBottom(new DamageAction(player, new DamageInfo(player, this.magicNumber, DamageInfo.DamageType.HP_LOSS)));



    }

}
