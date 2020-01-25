import java.util.Properties

fun Properties.getString(key : String) : String = this[key].toString()

fun Properties.getInt(key : String) : Int = this[key].toString().toInt()