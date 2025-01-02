package view

import scalafxml.core.macros.sfxml
import scalafx.scene.control.{TextField, PasswordField, Alert}
import scalafx.scene.control.Alert.AlertType
import model.User

@sfxml
class LoginController(
    private val usernameField: TextField,
    private val passwordField: PasswordField
) {
  def handleLogin(): Unit = {
    val username = usernameField.text.value.trim
    val password = passwordField.text.value.trim

    User.login(username, password) match {
      case Some(user) => showAlert(AlertType.Information, "Login Successful", s"Welcome back, $username!")
      case None => showAlert(AlertType.Error, "Login Failed", "Invalid credentials.")
    }
  }

  def handleRegister(): Unit = {
    val username = usernameField.text.value.trim
    val password = passwordField.text.value.trim

    User.register(username, password) match {
      case Some(user) => showAlert(AlertType.Information, "Registration Successful", s"Welcome, $username!")
      case None => showAlert(AlertType.Error, "Registration Failed", "Username already exists.")
    }
  }

  private def showAlert(alertType: AlertType, title: String, content: String): Unit = {
    new Alert(alertType) {
      this.title = title
      headerText = null
      contentText = content
    }.showAndWait()
  }
}