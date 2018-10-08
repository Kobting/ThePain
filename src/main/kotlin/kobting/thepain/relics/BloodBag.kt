package kobting.thepain.relics


import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.relics.AbstractRelic
import kobting.thepain.actions.ModifyBloodChargesAction
import kobting.thepain.extras.BloodBottle
import kobting.thepain.patches.JCharacterPatches

class BloodBag : PainCustomRelic(ID, AbstractRelic.RelicTier.UNCOMMON, AbstractRelic.LandingSound.MAGICAL) {

    companion object {

        @JvmStatic
        val ID = "thepain:BloodBag"
    }

    override fun getUpdatedDescription(): String {
        return DESCRIPTIONS[0]
    }

    override fun onEquip() {
        super.onEquip()
        val player = AbstractDungeon.player
        val charges = JCharacterPatches.bloodBottle_f.get(player).bloodCount
        AbstractDungeon.actionManager.addToNextCombat(ModifyBloodChargesAction(charges + 3))

    }

    override fun update() {
        super.update()
        if(AbstractDungeon.getCurrRoom().isBattleOver) {

        }
    }



    override fun atBattleStart() {
        println("ThePain: Adding 3 to blood charges.")
        val player = AbstractDungeon.player
        val charges = JCharacterPatches.bloodBottle_f.get(player).bloodCount
        AbstractDungeon.actionManager.addToTop(ModifyBloodChargesAction(charges + 3))
    }
}