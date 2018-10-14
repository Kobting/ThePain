package kobting.thepain.helpers

import kobting.thepain.powers.Protection

object PowerHelper {

    private var imagePaths = ImageMap("kobting/thepain/images/powers/")
    private val DEFAULT_IMAGE_NAME = "protection.png"

    init {
        initImagePaths()
    }

    private fun initImagePaths() {
        imagePaths[Protection.id] = "protection.png"
    }


    fun getImagePath(ID: String): String {
        return imagePaths.getOrDefault(ID, DEFAULT_IMAGE_NAME)
    }

}