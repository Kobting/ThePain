package kobting.thepain.patches

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.megacrit.cardcrawl.actions.GameActionManager
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.rooms.AbstractRoom
import com.megacrit.cardcrawl.rooms.MonsterRoom
import kobting.thepain.actions.ModifyBloodChargesAction
import kobting.thepain.extras.BloodBottleOnSelfDamage
import kobting.thepain.extras.BloodBottleSubscriber

class CharacterPatches {


    companion object {

        @JvmStatic
        private var onSelfDamageSubscribers = arrayListOf<BloodBottleOnSelfDamage>()

        @JvmStatic
        fun subscribeToOnSelfDamage(subscriber: BloodBottleOnSelfDamage){
            onSelfDamageSubscribers.add(subscriber)
        }

    }

    @SpirePatch(
            clz = AbstractPlayer::class,
            method = "renderHand"
    )
    class RenderPatch {

        companion object {

            @JvmStatic
            fun Prefix(instance: AbstractPlayer, sb: SpriteBatch) {
                if(AbstractDungeon.getCurrRoom()?.phase == AbstractRoom.RoomPhase.COMBAT || AbstractDungeon.getCurrRoom() is MonsterRoom && !instance.isDead){
                    val color = sb.color
                    sb.color = Color.WHITE
                    JCharacterPatches.bloodBottle_f.get(instance)?.render(sb) ?: println("BloodBottle is null")
                    sb.color = color
                }
            }
        }

    }

    @SpirePatch(
            clz = AbstractPlayer::class,
            method = "combatUpdate"
    )
    class CombatUpdatePatch{

        companion object {

            @JvmStatic
            fun Prefix(instance: AbstractPlayer){
                JCharacterPatches.bloodBottle_f.get(instance)?.update() ?: println("BloodBottle is null")
            }

        }

    }


    @SpirePatch(
            clz = AbstractPlayer::class,
            method = "damage"
    )
    class DamagePatch{

        companion object {

            @JvmStatic
            fun Prefix(instance: AbstractPlayer, info: DamageInfo){
                if(info.owner == instance && info.type == DamageInfo.DamageType.HP_LOSS) {
                    var damageAmount = info.output
                    if(instance.hasPower("IntangiblePlayer")) {
                        damageAmount = 1
                    }
                    onSelfDamageSubscribers.forEach {
                        it.onSelfDamage(damageAmount)
                    }
                }
            }

        }

    }

    @SpirePatch(
            clz = AbstractPlayer::class,
            method = "onVictory"
    )
    class OnVictoryPatch(){

        companion object {
            @JvmStatic
            fun Prefix(instance: AbstractPlayer){
                JCharacterPatches.bloodBottle_f.get(instance).bloodCount = 0
                JCharacterPatches.bloodBottle_f.get(instance).retainTurns = 0
                ApplyStartOfTurnRelicsPatch.realTurn = 0
            }
        }


    }

    @SpirePatch(
            clz = AbstractPlayer::class,
            method = "applyStartOfTurnRelics"
    )
    class ApplyStartOfTurnRelicsPatch(){

        companion object {
            var realTurn = 0

            @JvmStatic
            fun Prefix(instance: AbstractPlayer){
                realTurn++
                if(JCharacterPatches.bloodBottle_f.get(instance).retainTurns > 0){
                    println("ThePain: decrementing blood retain")
                    JCharacterPatches.bloodBottle_f.get(instance).retainTurns--
                }
                else if(realTurn != 1){ //Allow for relics to use atBattleStart methods
                    println("ThePain: Decrementing blood charges at start of turn")
                    AbstractDungeon.actionManager.addToBottom(ModifyBloodChargesAction(0))
                } else {
                    println("ThePain: Not doing anything, turn ${GameActionManager.turn}")
                }
            }

        }

    }

}