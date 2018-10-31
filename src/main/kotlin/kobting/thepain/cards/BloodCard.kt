package kobting.thepain.cards


abstract class BloodCard(ID: String, cost: Int, type: CardType,color: CardColor, rarity: CardRarity, target: CardTarget) :
        PainCustomCard(ID, cost, type, color, rarity, target) {

    var bloodToUse: Int = 0

}