package kobting.thepain.patches;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import kobting.thepain.extras.BloodBottle;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = SpirePatch.CLASS
)
public class JCharacterPatches {

    private static Texture texture = ImageMaster.loadImage("kobting/thepain/images/blood_bottle.png");
    private static BloodBottle bloodBottle = new BloodBottle(texture);

    public static SpireField<BloodBottle> bloodBottle_f = new SpireField<>(() -> bloodBottle);
}
