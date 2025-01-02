package model

import scala.collection.mutable.ListBuffer

case class Leaderboard() {
  private val scores = ListBuffer[(String, Int)]()

  def addScore(username: String, score: Int): Unit = {
    scores += ((username, score))
    scores.sortBy(-_._2)
  }

  def getTopScores(limit: Int = 10): List[(String, Int)] = scores.take(limit).toList
}