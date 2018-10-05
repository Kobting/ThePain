package kobting.thepain.helpers

class ImageMap(private var srcPackageName: String, private var imagePackageName: String, private var modString: String?) : HashMap<String, String>() {

    override fun put(key: String, value: String): String? {
        val key_ = key.replace(srcPackageName, "")
        println("ThePain: Putting new key $key")
        return super.put(modString + key_, imagePackageName + value)
    }

    fun getOrDefaultNoModIDPrefix(key: String, defaultValue: String): String {
        return super.getOrDefault(modString + key, imagePackageName + defaultValue)
    }

    override fun get(key: String): String? {
        return super.get(modString + key)
    }

    fun containsKeyNoModIDPrefix(key: String): Boolean {
        return super.containsKey(modString + key)
    }

    override fun getOrDefault(key: String, defaultValue: String): String {
        return super.getOrDefault(key, imagePackageName + defaultValue)
    }
}