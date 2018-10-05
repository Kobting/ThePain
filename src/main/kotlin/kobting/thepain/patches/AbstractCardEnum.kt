package kobting.thepain.patches

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum
import com.megacrit.cardcrawl.cards.AbstractCard

class AbstractCardEnum {

    companion object {
        @SpireEnum
        @JvmStatic
        var THE_PAIN_PURPLE: AbstractCard.CardColor? = null
    }

}
