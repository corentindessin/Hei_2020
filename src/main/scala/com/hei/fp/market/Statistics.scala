package com.hei.fp.market

class Statistics(data:Array[Double]) {

  def meanStd(x: Array[Double]): (Double, Double) = {
    @scala.annotation.tailrec
    def meanStd(x: Array[Double], mu: Double, Q: Double, count: Int): (Double, Double) =
      if (count >= x.length) (mu, Math.sqrt(Q / x.length))
      else {
        val newCount = count + 1
        val newMu = x(count) / newCount + mu * (1.0 - 1.0 / newCount)
        val newQ = Q + (x(count) - mu) * (x(count) - newMu)
        meanStd(x, newMu, newQ, newCount)
      }

    meanStd(x, 0.0, 0.0, 0)
  }

  /** compute moving average : data - number of periods */
  def movingAverage(n: Int): Array[Double] = (1 to data.length).map {
    case x if x < n => 0.0
    case x => meanStd( data.slice(x - n , x) )._1
  }.toArray

  /** compute moving standard deviation : data - number of periods */
  def movingStd(n: Int): Array[Double] = (1 to data.length).map {
    case x if x < n => 0.0
    case x => meanStd( data.slice(x - n , x) )._2
  }.toArray

}