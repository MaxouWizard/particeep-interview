package basic

/**
 * Compute the average of the list
 *
 * ex : [1, 10, 16] -> 9
 */
object ComputeAverage {

  /* Would Option[Double] be better ? Probably not, we might just end up doing .getOrElse(0), anyway, that would be an easy refactor */
  def average(l: List[Double]): Double = if (l.isEmpty) 0 else l.sum / l.size

}
