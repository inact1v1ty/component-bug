import korolev.Context
import korolev.execution._
import scala.concurrent.Future
import korolev.state.javaSerialization._

sealed trait MyState


object MyState {
  val globalContext = Context[Future, MyState, Any]

  case class DefaultState(component: Int = 0) extends MyState

}
