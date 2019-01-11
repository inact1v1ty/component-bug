import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.{ActorMaterializer, Materializer}
import korolev._
import korolev.akkahttp._
import korolev.execution._
import korolev.server._
import korolev.state.javaSerialization._
import scala.concurrent.Future

object componentBug extends App {

  private implicit val actorSystem: ActorSystem = ActorSystem()
  private implicit val materializer: Materializer = ActorMaterializer()

  val applicationContext = Context[Future, MyState, Any]

  import MyState.globalContext._
  import MyState._
  import symbolDsl._

  private val config = KorolevServiceConfig[Future, MyState, Any](
    stateStorage = StateStorage.default(DefaultState()),
    router = emptyRouter,
    render = {
      case DefaultState(value) =>
        'div(
          'a(
            'href /= "#",
            "Component A",
            event('click)({ ui =>
              ui.transition(_ => DefaultState())
            })
          ),
          'br(),
          'a(
            'href /= "#",
            "Component B",
            event('click)({ ui =>
              ui.transition(_ => DefaultState(1))
            })
          ),
          'br(),
          'div(value.toString),
          'br(),
          if(value == 0) {
            ComponentA(ComponentAState.DefaultState())
              .silent(ComponentAParameters())
          } else {
            ComponentB(ComponentBState.HrefGoto())
              .silent(ComponentBParameters())
          }
        )
    }
  )

  private val route = akkaHttpService(config).apply(AkkaHttpServerConfig())

  Http().bindAndHandle(route, "0.0.0.0", 8080)
}
