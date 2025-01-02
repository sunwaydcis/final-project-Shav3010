package model

import scala.collection.mutable
import java.security.MessageDigest

case class User(username: String, passwordHash: String) {
  private var highScore = 0

  def setHighScore(score: Int): Unit = {
    if (score > highScore) highScore = score
  }

  def getHighScore: Int = highScore
}

object User {
  private val users = mutable.Map[String, User]()

  def register(username: String, password: String): Option[User] = {
    if (users.contains(username)) None
    else {
      val passwordHash = hashPassword(password)
      val user = User(username, passwordHash)
      users(username) = user
      Some(user)
    }
  }

  def login(username: String, password: String): Option[User] = {
    users.get(username).filter(_.passwordHash == hashPassword(password))
  }

  private def hashPassword(password: String): String = {
    val md = MessageDigest.getInstance("SHA-256")
    md.digest(password.getBytes).map("%02x".format(_)).mkString
  }
}