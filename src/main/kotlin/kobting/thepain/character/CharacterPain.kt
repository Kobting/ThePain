package kobting.thepain.character

import basemod.abstracts.CustomPlayer
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.MathUtils
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.EnergyManager
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.helpers.FontHelper
import com.megacrit.cardcrawl.powers.AbstractPower
import com.megacrit.cardcrawl.screens.CharSelectInfo
import kobting.thepain.cards.CutDry
import kobting.thepain.cards.Defend_Pain
import kobting.thepain.cards.Strike_Pain
import kobting.thepain.patches.ThePainCharacterEnum
import kobting.thepain.relics.ShatteredGlass

import java.util.ArrayList

class CharacterPain(name: String) : CustomPlayer(name, ThePainCharacterEnum.THE_PAIN, orbs, orbVfx, null as String?, null as String?) {

    companion object {

        private val orbs = arrayOf(
                "kobting/thepain/images/characters/orb/layer1.png",
                "kobting/thepain/images/characters/orb/layer2.png",
                "kobting/thepain/images/characters/orb/layer3.png",
                "kobting/thepain/images/characters/orb/layer4.png",
                "kobting/thepain/images/characters/orb/layer5.png",
                "kobting/thepain/images/characters/orb/layer6.png",
                "kobting/thepain/images/characters/orb/layer1d.png",
                "kobting/thepain/images/characters/orb/layer2d.png",
                "kobting/thepain/images/characters/orb/layer3d.png",
                "kobting/thepain/images/characters/orb/layer4d.png",
                "kobting/thepain/images/characters/orb/layer5d.png")

        private const val orbVfx = "kobting/thepain/images/characters/orb/vfx.png"

    }
    

    init {

        this.dialogX = this.drawX + 0.0f * Settings.scale
        this.dialogY = this.drawY + 220.0f * Settings.scale

        initializeClass(null, "images/characters/ironclad/shoulder2.png", "images/characters/ironclad/shoulder.png", "images/characters/ironclad/corpse.png",
                getLoadout(), 20.0f, -10.0f, 220.0f, 290.0f, EnergyManager(3))

        loadAnimation("images/characters/ironclad/idle/skeleton.atlas", "images/characters/ironclad/idle/skeleton.json", 1.0f)

        val e = this.state.setAnimation(0, "animation", true)
        e.time = e.endTime * MathUtils.random()

        initializeStarterRelics(this.chosenClass)

    }

    override fun getStartingRelics(): ArrayList<String> {
        val relics = ArrayList<String>()

        relics.add(ShatteredGlass.ID)

        return relics    }

    override fun getLoadout(): CharSelectInfo {
        return CharSelectInfo(
                "The Pain",
                "Use HP as a resource for killing",
                80,
                80,
                0,
                99,
                5,
                this,
                getStartingRelics(),
                getStartingDeck(),
                false)    }

    override fun getCardColor(): Color {
        return Color.MAROON
    }

    override fun getEnergyNumFont(): BitmapFont {
        return FontHelper.energyNumFontRed
    }

    override fun getAscensionMaxHPLoss(): Int {
        return 7
    }

    override fun getCustomModeCharacterButtonSoundKey(): String {
        return "ATTACK_FIRE"
    }

    override fun getStartingDeck(): ArrayList<String> {
        val deck = ArrayList<String>()

        deck.add(Strike_Pain.ID)
        deck.add(Strike_Pain.ID)
        deck.add(Strike_Pain.ID)
        deck.add(Strike_Pain.ID)
        deck.add(Strike_Pain.ID)
        deck.add(Defend_Pain.ID)
        deck.add(Defend_Pain.ID)
        deck.add(Defend_Pain.ID)
        deck.add(Defend_Pain.ID)
        deck.add(Defend_Pain.ID)
        deck.add(CutDry.ID)


        return deck    }

    override fun doCharSelectScreenSelectEffect() {
    }

    override fun getStartCardForEvent(): AbstractCard {
        return CutDry()
    }

    override fun getTitle(playerClass: PlayerClass?): String {
        return "The Pain"
    }

    override fun newInstance(): AbstractPlayer {
        return CharacterPain("The Pain");
    }

    override fun getLocalizedCharacterName(): String {
        return "The Pain"
    }

    override fun getCardTrailColor(): Color {
        return Color.MAROON
    }


}
