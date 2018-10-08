package kobting.thepain

import basemod.BaseMod
import basemod.interfaces.*
import com.badlogic.gdx.Gdx
import com.megacrit.cardcrawl.localization.*
import com.badlogic.gdx.graphics.Color
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer
import com.megacrit.cardcrawl.core.CardCrawlGame
import kobting.thepain.character.CharacterPain
import kobting.thepain.extras.BloodBottleOnSelfDamage
import kobting.thepain.helpers.CardHelper
import kobting.thepain.helpers.RelicHelper
import kobting.thepain.patches.AbstractCardEnum
import kobting.thepain.patches.CharacterPatches
import kobting.thepain.patches.ThePainCharacterEnum
import kobting.thepain.powers.Relief
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import java.nio.charset.StandardCharsets


@SpireInitializer
class ThePainMod :
        EditStringsSubscriber, EditCharactersSubscriber,
        EditKeywordsSubscriber {


    init {
        BaseMod.subscribe(this)
    }

    override fun receiveEditStrings() {
        BaseMod.loadCustomStrings(RelicStrings::class.java, Gdx.files.internal("kobting/thepain/localization/eng/thepain-relics.json").readString(StandardCharsets.UTF_8.toString()))
        BaseMod.loadCustomStrings(PowerStrings::class.java, Gdx.files.internal("kobting/thepain/localization/eng/thepain-powers.json").readString(StandardCharsets.UTF_8.toString()))
        BaseMod.loadCustomStrings(CardStrings::class.java, Gdx.files.internal("kobting/thepain/localization/eng/thepain-cards.json").readString(StandardCharsets.UTF_8.toString()))

    }

    override fun receiveEditCharacters() {
        BaseMod.addCharacter(CharacterPain::class.java, "The Pain", "The Pain Class", AbstractCardEnum.THE_PAIN_PURPLE,
                "The Pain", "kobting/thepain/images/characters/select.png", "kobting/thepain/images/characters/portrait.png", ThePainCharacterEnum.THE_PAIN)
    }

    override fun receiveEditKeywords() {

        val protection = arrayOf("Protection", "protection")
        BaseMod.addKeyword(protection, "Heal for all HP lost this turn at the end of the turn.")

        val relief = arrayOf("Relief", "relief")
        BaseMod.addKeyword(relief, CardCrawlGame.languagePack.getPowerStrings(Relief.id).DESCRIPTIONS[0])
    }

    companion object {

        private var logger: Logger? = null
        private val color = Color(102f, 0f, 0f, 1f)

        //Method needed for @SpireInitializer
        @JvmStatic
        fun initialize() {
            logger = LogManager.getLogger(ThePainMod::class.java.name)


            logger!!.log(Level.INFO, "-------------Initializing: " + AbstractCardEnum.THE_PAIN_PURPLE.toString() + "----------------------")

            BaseMod.addColor(AbstractCardEnum.THE_PAIN_PURPLE, color, color, color, color, color, color, color,
                    "kobting/thepain/images/ui/bg_attack_purple.png", "kobting/thepain/images/ui/bg_skill_purple.png",
                    "kobting/thepain/images/ui/bg_power_purple.png", "kobting/thepain/images/ui/card_purple_orb.png",
                    "kobting/thepain/images/ui/bg_attack_purple_p.png", "kobting/thepain/images/ui/bg_skill_purple_p.png",
                    "kobting/thepain/images/ui/bg_power_purple_p.png", "kobting/thepain/images/ui/card_purple_orb_p.png")


            //Possibly make them subscribe to BaseMod?
            CardHelper.getImagePath("")
            RelicHelper.getImagePath("")

            ThePainMod()


            logger!!.log(Level.INFO, "-------------Finished Initializing: " + AbstractCardEnum.THE_PAIN_PURPLE.toString() + "----------------------")


        }

        @JvmStatic
        fun subscribeToOnSelfDamage(subscriber: BloodBottleOnSelfDamage){
            CharacterPatches.subscribeToOnSelfDamage(subscriber)
        }


    }
}
