package kobting.thepain.cards

import basemod.abstracts.CustomCard
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.helpers.CardHelper


abstract class PainCustomCard(ID: String, cost: Int, type: AbstractCard.CardType, color: AbstractCard.CardColor?, rarity: AbstractCard.CardRarity, target: AbstractCard.CardTarget) : CustomCard(ID, "", "kobting/thepain/images/cards/beta_purple.png", cost, "", type, color, rarity, target) {

    private var cardStrings: CardStrings? = null

    init {

        this.cardStrings = CardCrawlGame.languagePack.getCardStrings(ID)

        this.name = cardStrings?.NAME ?: "Error: No Name"
        this.initializeTitle()

        this.rawDescription = cardStrings?.DESCRIPTION ?: "Error: No Description"
        this.initializeDescription()

        this.textureImg = CardHelper.getImagePath(ID)
        this.loadCardImage(textureImg)

    }


    fun upgradeDescription() {
        val newDescription = this.cardStrings?.UPGRADE_DESCRIPTION ?: ""
        if (newDescription != "") {
            this.rawDescription = newDescription
            this.initializeDescription()
        }
    }

}
