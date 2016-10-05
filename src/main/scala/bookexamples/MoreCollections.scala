/**
 * Created by tsidhu15 on 10/5/16.
 */

object MoreCollections extends App {
  def union[A] (set1: Set[A], set2: Set[A]): Set[A] = {
  // THIS WORKS
    //    val test = for {
//      item1 <- set1
//      item2 <- set2
//    } yield Set(item2, item1)
//
//    test.flatMap(_)


    /// LETS TRY BOOK SOLUTION
       set1.foldLeft(set2)( (set, elem) => set + elem)
  }

  def unionMaps[A, Int] (map1: Map[A,Int], map2: Map[A,Int]): Map[A,Int] = {
    map1.foldLeft(map2) { (map, elem) =>
      val v: Int = map.getOrElse(elem._1, elem._2) // map.get(elem._1).getOrElse(0) THIS DOESN"T WORK FOR SOME REASON?
      map + (elem._1 -> (elem._2 + v))}
  }
}