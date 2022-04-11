package basic

/**
 * What is the complexity of the function ?
 *      => Complexity is O(n^2)
 *      => It also doesn't work at all and always return an empty list as `categories` is a val and never mutates.
 *
 * Refactor it to be a better function
 *
 *      => Make return type a Set[T], we probably don't want to deal with duplicates
 *      => Map files to categories and make it a Set.
 *      => Avoid null checking as it is Scala code, nothing should be null, at best, List.empty[File] or else parameter type should be Option[List[File]]
 *      => New complexity is still O(2n)
 *
 */
object Refactoring {

  case class File(name: String, category: String)

  def getCategories(files: List[File]): Set[String] = files.map(_.category).toSet
}
