package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import helpers.CardHelper;


public abstract class PainCustomCard extends CustomCard {

    private CardStrings cardStrings;
    private CardHelper cardHelper;

    public PainCustomCard(String ID, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(ID, "", "images/cards/beta_purple.png", cost, "", type, color, rarity, target);

        cardHelper = CardHelper.getInstance();
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

        this.name = cardStrings.NAME;
        this.initializeTitle();

        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();

        this.textureImg = cardHelper.getImagePath(ID);
        this.loadCardImage(textureImg);

    }

    public void upgradeDescription(){
        String newDescription = cardStrings.UPGRADE_DESCRIPTION;
        if(!newDescription.equals("") || newDescription != null) {
            this.rawDescription = newDescription;
            this.initializeDescription();
        }
    }
}
