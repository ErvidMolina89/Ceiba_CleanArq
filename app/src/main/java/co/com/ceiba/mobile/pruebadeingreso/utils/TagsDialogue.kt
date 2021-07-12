package co.com.ceiba.mobile.pruebadeingreso.utils

enum class TagsDialogue {
    DialogGeneric
    ;
    fun getTags() : String{
        return when(this){
            DialogGeneric -> "DialogGeneric"
        }
    }
}