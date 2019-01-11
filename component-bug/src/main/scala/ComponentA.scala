import korolev.{Component, KorolevTemplateDsl}
import korolev.execution.{defaultExecutor, _}
import korolev.state.javaSerialization._
import slogging.LazyLogging
import scala.concurrent.Future

final case class ComponentAParameters()

sealed trait ComponentAState
object ComponentAState {
  final case class DefaultState()
    extends ComponentAState
}

case class ComponentA(initState: ComponentAState)
  extends Component[Future, ComponentAState, ComponentAParameters, Unit](initState)
    with LazyLogging {

  import context.symbolDsl._

  implicit val symbolDsl: KorolevTemplateDsl[Future, ComponentAState, Unit] = context.symbolDsl

  override def render(parameters: ComponentAParameters, state: ComponentAState): Node = {

    state match {
      case default@ComponentAState.DefaultState() =>
        'div ("Hello from component A")
    }
  }
}
