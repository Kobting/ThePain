package kobting.thepain.helpers

class ImageMap(private var imagePackageName: String) : HashMap<String, String>() {

    override fun put(key: String, value: String): String? {
        return super.put(key, imagePackageName + value)
    }

    override fun getOrDefault(key: String, defaultValue: String): String {
        return super.getOrDefault(key, imagePackageName + defaultValue)
    }
}