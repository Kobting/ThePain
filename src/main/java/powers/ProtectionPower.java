package powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ProtectionPower extends AbstractPower {

    private int lostHP = 0;

    public ProtectionPower(AbstractPlayer owner) {
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = 1;
        this.ID = "ProtectionPower";
        this.name = "Protection";
        this.description = "Heal for all self damage taken this turn.";
        this.img = new Texture("images/powers/protection.png");
    }

    //TODO: Fix this so that thorns doesn't count towards it
    @Override
    public int onLoseHp(int damageAmount) {
        lostHP += damageAmount;
        return super.onLoseHp(damageAmount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            AbstractDungeon.actionManager.addToBottom(new HealAction(this.owner, this.owner, lostHP));
        }
        this.lostHP = 0;
        flash();
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this.ID));
    }

}
