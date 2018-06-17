package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import initliziers.ThePainInitializer;


public class ShatteredGlass extends CustomRelic {

    public static final String ID = "Shattered_Glass";

    private int lostHPAmount = 0;
    private boolean trackHPLost = true;

    public ShatteredGlass() {
        super(ID, ThePainInitializer.getStarterRelicTexture(), ThePainInitializer.getStarterRelicTexture() ,RelicTier.STARTER, LandingSound.MAGICAL);
    }


    @Override
    public AbstractRelic makeCopy() {
        return new ShatteredGlass();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onLoseHp(int damageAmount) {
        super.onLoseHp(damageAmount);
        if(trackHPLost){
            lostHPAmount+=damageAmount;
            this.setCounter(lostHPAmount);
        }

    }


    @Override
    public void onPlayerEndTurn() {
        super.onPlayerEndTurn();

        if(lostHPAmount > 0) {
            MonsterGroup monsters = AbstractDungeon.getCurrRoom().monsters;

            for(AbstractMonster monster : monsters.monsters) {
                monster.damage(new DamageInfo(AbstractDungeon.player, lostHPAmount * 2, DamageInfo.DamageType.THORNS));
            }

            lostHPAmount = 0;
            this.setCounter(lostHPAmount);
        }

        trackHPLost = false;
    }

    @Override
    public void onVictory() {
        super.onVictory();
        lostHPAmount = 0;
        this.setCounter(lostHPAmount);
        trackHPLost = false;
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        trackHPLost = true;
    }
}
