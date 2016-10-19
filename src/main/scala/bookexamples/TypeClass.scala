/**
 * Created by tsidhu15 on 10/16/16.
 */

object TypeClass extends App {
   val minOrdering = Ordering.fromLessThan[Int](_ < _)
   val absOrdering = Ordering.fromLessThan[Int](Math.abs(_) < Math.abs(_))

  List(2, 4, 3).sorted(minOrdering)

  List(2, 4, 3).sorted(absOrdering)


  trait Equal[A]{
    def equal(a: A, b: A): Boolean
  }

  case class Person(name: String, email: String)

  object PersonEqual {
    implicit object PersonEquals extends Equal[Person] {
      def equal(a: Person, b: Person): Boolean = {
        return a.email == b.email
      }
    }
  }

  object NameEmailEqual {
    def equal(v1: Person, v2: Person): Boolean = v1.email == v2.email && v1.name == v2.name
  }

  object Eq {
    def apply[A](a: A, b: A )(implicit c: Equal[A]): Boolean = {
      c.equal(a, b)
    }
  }

  object EqualImplicit {
    def byEmail: Unit = {
      import PersonEqual._
      Eq(Person("Noel", "noel@example.com"), Person("Noel", "noel@example.com"))
    }
  }

  object IntImplicits {
     implicit class IntImplicit(data: Int){
       def yea(): Unit ={
//         for(int i = 0; i < data; i++){
//           println("Oh yea")
//         }
         for {
          x <- List.range(0, data)
         }println("Oh yea")

         //using times

         times(i => println("Oh yea"))

       }

       def times (f: (Int => Unit)): Unit = {
         for {
           _ <- 0 until data
         }f(_)
       }
     }
  }


  object TripleEqual {
    implicit class triple(a: String){
      def === (b: String): Boolean ={
        a == b
      }
    }
  }


}