import java.text.DecimalFormat
import kotlin.time.Duration.Companion.ZERO
import kotlin.time.Duration.Companion.milliseconds

const val workHoursPerYear = 2080.00 // Based on 40 work hours a week and 52 work weeks in a year

// Salary calculation example
// If I make 100,000 a year
// That means I work for 2,096 hours a year (estimated based on number of 'work days' in a year)
// 100,000 / 2,096 = 47.709923664122137 dollars an hour divide by 60 to get minutely salary

fun main() {
    println("Welcome to the meeting cost calculator. This program will try to calculate how much money it cost(s) to be in a meeting. This is based on number of people in the meeting, a salary estimate for each person, and the length of the meeting.")
    println("This is just a fun way to see how much money you are burning in a meeting...")
    println("Current assumptions: Everyone works a 52 week year - everyone is working 40 hours a week - everyone is paid salary - no one uses/has PTO throughout the year")
    // User prams
    var option: String? = null

    println("I need some information")
    while (option == null) {
        println("How would you like to run this?")
        print(
            """
1) Basic (enter number of people, salary average, and time to get a result)
2) Advanced (enter number of people, salary average, and watch as the time passes just how much you are losing)
3) Expert (as a meeting goes on, I will continue to tally costs, but also ask if params have changed throughout the meeting (i.e. people joined & costs increased)
4) Exit (you hate me and started me by accident)

Choose: 
""".trimIndent()
        )
        option = readln()
        if (option !in listOf("1", "2", "3", "4")) {
            println("Invalid option, please select 1-4")
            option = null
        }
        when (option) {
            "1" -> runBasic()
            "2" -> runAdvanced()
            "3" -> runExpert()
            "4" -> return
            else -> continue
        }
        option = null
    }
}

fun runBasic() {
    println("\n---BASIC---")
    print("Enter the number of people who attended the meeting (Example 5): ")
    val personCount = readln().toInt()
    print("Enter the average per-year salary of the people in the meeting (Example: 100000): ")
    val averagePerYearSalary = readln().toDouble()
    val averageMinutelySalary = (averagePerYearSalary / workHoursPerYear) / 60.00
    print("Enter the length of the meeting in minutes (Example: 30, 42, 60, 90, 420, etc.): ")
    val meetingLengthInMinutes = readln().toInt()

    println("--\nC&P friendly output:\n--")
    println("Assuming no one was doing any work during the meeting. And assuming the following:\nNumber of people who attended the meeting: $personCount\nAverage per-year salary of the people in the meeting: \$${DecimalFormat("#,##0.00").format(averagePerYearSalary)}\nLength of the meeting in minutes: $meetingLengthInMinutes")
    println("Total meeting cost is approximately... $${DecimalFormat("#,##0.00").format(personCount * averageMinutelySalary * meetingLengthInMinutes)}")
    println("---BASIC END---\n")
}


fun runAdvanced() {
    println("\n---ADVANCED---")
    print("""
Here's how this works:
I will ask you some basic information about this meeting you are about to be in, or are already in.
Then, I will continue to run, accumulating the cost of this meeting in real time until you terminate this app (because Kotlin Native keypress listeners are hard apparently)

Let's begin...
""".trimIndent())
    print("Enter the number of people who are attending this meeting (in it now or estimate the max): ")
    val personCount = readln().toInt()
    print("Enter the average per-year salary of the people in the meeting (Example: 100000): ")
    val averageMinutelySalary = (readln().toDouble() / workHoursPerYear) / 60.00
//    print("Enter the length of the meeting in minutes (Example: 30, 60, 90, 92, etc.): ")
//    val meetingLengthInMinutes = readln().toInt()

    println("Alright, lets start counting!")
    var currentTotal = 0.00
    var duration = ZERO

    while(true) {
        Thread.sleep(5000)
        duration = duration.plus(5000.milliseconds)
        currentTotal +=  personCount * ((averageMinutelySalary / 60) * 5)
        println("Total Time: ${duration.inWholeHours}h:${duration.inWholeMinutes}m:${duration.inWholeSeconds % 60}s, Current total is: \$${DecimalFormat("#,##0.00").format(currentTotal)}")
    }
//    println("\n---ADVANCED END---")
}

fun runExpert() {
    TODO("Not implemented yet")
}
