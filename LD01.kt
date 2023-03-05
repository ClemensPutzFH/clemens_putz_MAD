import java.util.*

fun main() {

    game(createNumber())

}

fun createNumber(): String {
    var list = (0..9).toList()
    var string = ""
    var helper = 0
    for(i in 0..3){
        helper = list.random()
        string += helper
        list = list.filter{it != helper}
    }
    return string
}

fun game(number: String){
    println("A random 4digit number has been created - take a guess!")
    val scanner  = Scanner(System.in)
    var n = 0
    var m = 0
    var guess = ""
    while(m != 4){
        guess = scanner.nextLine()
        var charArr = guess.toCharArray()
        if(charArr.size != 4){
            println("Your guess has the wrong size- try again!")
        }else{
            m = calculateM(guess, number)
            n = calculateN(guess, number)
            if(m != 4){
                println("Wrong - take this hint: $n:$m...")
                println("...and a new guess!")
            }
        }
    }
    println("Congrats! You did it!")
}

val calculateM: (String, String) -> Int = {
        guess, number ->
    var newM = 0
    var charArrGuess = guess.toCharArray()
    var charArrNumber = number.toCharArray()
    for(i in 0..3){
        if(charArrGuess[i] == charArrNumber[i]) {
            newM += 1
        }
    }
    newM
}

val calculateN: (String, String) -> Int = {
        guess, number ->
    var newN = 0
    var charArrGuess = guess.toCharArray()
    var charArrNumber = number.toCharArray()
    var list = charArrGuess.toList()
    var cleaner: Char = '0'
    while(list.isNotEmpty()){
        if(charArrNumber.contains(list[0])){
            newN += 1
        }
        cleaner = list[0]
        list = list.filter {it != cleaner}
    }
    newN
}
