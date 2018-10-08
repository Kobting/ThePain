package kobting.thepain.extras

interface BloodBottleOnSelfDamage : BloodBottleSubscriber {

    fun onSelfDamage(damage: Int)

}