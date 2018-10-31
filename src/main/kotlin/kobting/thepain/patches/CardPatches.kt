package kobting.thepain.patches

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum
import com.evacipated.cardcrawl.modthespire.lib.SpireField
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import kobting.thepain.cards.BloodCard

class CardPatches {

    @SpirePatch(
            clz = AbstractCard::class,
            method = "hasEnoughEnergy"
    )
    class HasEnoughEnergyPatch{

        companion object {

            @JvmStatic
            fun prefix(instance: AbstractCard) : SpireReturn<Boolean>{
                val bloodCount = JCharacterPatches.bloodBottle_f.get(AbstractDungeon.player).bloodCount
                if(instance is BloodCard){
                    val card : BloodCard = instance
                    if(card.bloodToUse < bloodCount){
                        instance.cantUseMessage = "Do not have enough blood charges."
                        return SpireReturn.Return(false)
                    } else {
                        return SpireReturn.Continue()
                    }
                } else {
                    return SpireReturn.Continue()
                }
            }

        }

    }


}