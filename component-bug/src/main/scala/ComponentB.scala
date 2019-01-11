import korolev.{Component, KorolevTemplateDsl}
import korolev.execution.{defaultExecutor, _}
import korolev.state.javaSerialization._
import slogging.LazyLogging
import scala.concurrent.Future

final case class ComponentBParameters()

sealed trait ComponentBState
object ComponentBState {
  final case class HrefGoto()
    extends ComponentBState
}

case class ComponentB(initState: ComponentBState)
  extends Component[Future, ComponentBState, ComponentBParameters, Unit](initState)
    with LazyLogging {

  import context.symbolDsl._

  implicit val symbolDsl: KorolevTemplateDsl[Future, ComponentBState, Unit] = context.symbolDsl

  override def render(parameters: ComponentBParameters, state: ComponentBState): Node = {

    state match {
      case default@ComponentBState.HrefGoto() =>
        'div ("Hello from component B")
    }
  }
}
