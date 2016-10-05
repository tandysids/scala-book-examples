package bookexamples

/**
 * Created by tsidhu15 on 10/4/16.
 */
object ForYield extends App {

  val people = Set( "Alice",
    "Bob",
    "Charlie",
    "Derek",
    "Edith",
    "Fred")
  val ages = Map( "Alice" -> 20, "Bob" -> 30, "Charlie" -> 50, "Derek" -> 40, "Edith" -> 10, "Fred" -> 60)
  val favoriteColors = Map( "Bob" -> "green", "Derek" -> "magenta", "Fred" -> "yellow")
  val favoriteLolcats = Map( "Alice" -> "Long Cat", "Charlie" -> "Ceiling Cat", "Edith" -> "Cloud Cat")

  def lookup[A] (name: String, map: Map[String, A]): Unit ={
    map.get(name)
  }

  def colorOldest(): String ={

//    val agesToPeople: Map[Int, String] = for {
//      (person, age) <- ages
//    } yield Map() + (age, person)

    val agesToPeople: Map[Int, String] = ages.map{case (k,v) => (v -> k)}

    favoriteColors.get(agesToPeople.get(findLargest(ages.map(x => x._2))).getOrElse("")).getOrElse("")

//    list.
  }

  def findLargest(list: Iterable[Int]): Int = {
    list match {
      case head :: Nil => head
      case head :: rest => Math.max(head, findLargest(rest))
    }
  }
}
